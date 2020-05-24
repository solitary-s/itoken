package com.aloneness.itoken.service.sso.controller;

import com.aloneness.itoken.common.domain.TbSysUser;
import com.aloneness.itoken.common.utils.MapperUtils;
import com.aloneness.itoken.common.utils.CookieUtils;
import com.aloneness.itoken.service.sso.service.LoginService;
import com.aloneness.itoken.service.sso.service.consumer.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(@RequestParam(required = false) String url,
                        HttpServletRequest request,
                        Model model){
        String token = CookieUtils.getCookieValue(request, "token");
        if(StringUtils.isNotBlank(token)){
            String loginCode = redisService.get(token);
            if(StringUtils.isNotBlank(loginCode)){
                String json = redisService.get(loginCode);
                if(StringUtils.isNotBlank(json)){
                    try {
                        TbSysUser tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
                        if(tbSysUser != null){
                            if(StringUtils.isNotBlank(url)){
                                return "redirect:" + url;
                            }
                        }
                        model.addAttribute("tbSysUser", tbSysUser);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        if(StringUtils.isNotBlank(url)){
            model.addAttribute("url", url);
        }

        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String loginCode,
                        @RequestParam(required = true) String password,
                        @RequestParam(required = false) String url,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        RedirectAttributes redirectAttributes){
        TbSysUser tbSysUser = loginService.login(loginCode, password);

        if(tbSysUser == null){
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误，请重新登录");
        }

        else {
            String token = UUID.randomUUID().toString();
            String result = redisService.put(token, loginCode, 60 * 60 * 24);
            if(StringUtils.isNotEmpty(result) && "ok".equals(result)){
                CookieUtils.setCookie(request, response, "token", token, 60 * 60 * 24);
                if(StringUtils.isNotEmpty(url)){
                    return "redirect:" + url;
                }
            }
            else {
                redirectAttributes.addFlashAttribute("message", "服务器异常，请稍后再试");
            }
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam(required = false) String url, Model model){
        try{
            CookieUtils.deleteCookie(request, response, "token");
        }catch (Exception e){
            e.printStackTrace();
        }
        return login(url, request, model);
    }
}
