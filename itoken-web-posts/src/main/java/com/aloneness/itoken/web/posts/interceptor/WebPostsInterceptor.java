package com.aloneness.itoken.web.posts.interceptor;

import com.aloneness.itoken.common.domain.TbSysUser;
import com.aloneness.itoken.common.utils.CookieUtils;
import com.aloneness.itoken.common.utils.HttpServletUtils;
import com.aloneness.itoken.common.utils.MapperUtils;
import com.aloneness.itoken.web.posts.service.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WebPostsInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request, "token");
        if(StringUtils.isBlank(token)){
            try{
                response.sendRedirect(String.format("http://localhost:8503/login?url=%s", HttpServletUtils.getFullPath(request)));
            }catch (Exception e){
                e.printStackTrace();
            }
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        TbSysUser tbSysUser = (TbSysUser) session.getAttribute("tbSysUser");

        if (tbSysUser != null){
            if(modelAndView != null){
                modelAndView.addObject("tbSysUser", tbSysUser);
            }
        }

        else {
            String token = CookieUtils.getCookieValue(request, "token");
            if(StringUtils.isNotBlank(token)){
                String loginCode = redisService.get(token);
                if(StringUtils.isNotBlank(loginCode)){
                    String json = redisService.get(loginCode);
                    if(StringUtils.isNotBlank(json)){
                        try{
                            tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
                            if(modelAndView != null){
                                modelAndView.addObject("tbSysUser", tbSysUser);
                            }
                            request.getSession().setAttribute("tbSysUser", tbSysUser);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        if(tbSysUser == null){
            try{
                response.sendRedirect(String.format("http://localhost:8503/login?url=%s", HttpServletUtils.getFullPath(request)));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
