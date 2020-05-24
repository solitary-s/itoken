package com.aloneness.itoken.web.posts.controller;

import com.aloneness.itoken.common.domain.TbPostsPost;
import com.aloneness.itoken.common.domain.TbSysUser;
import com.aloneness.itoken.common.dto.BaseResult;
import com.aloneness.itoken.common.dto.DataTablesResult;
import com.aloneness.itoken.common.utils.MapperUtils;
import com.aloneness.itoken.web.posts.service.PostsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Controller
public class PostsController {

    @Autowired
    private PostsService postsService;

    @ModelAttribute
    public TbPostsPost tbPostsPost(String postGuid){
        TbPostsPost tbPostsPost = null;

        if(StringUtils.isBlank(postGuid)){
            tbPostsPost = new TbPostsPost();
        } else {
            String json = postsService.getPostById(postGuid);
            try {
                tbPostsPost = MapperUtils.json2pojo(json, TbPostsPost.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(tbPostsPost == null){
                tbPostsPost = new TbPostsPost();
            }
        }
        return tbPostsPost;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbPostsPost tbPostsPost, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {

        tbPostsPost.setPostGuid(UUID.randomUUID().toString());
        tbPostsPost.setTimePublished(new Date());
        tbPostsPost.setStatus("1");

        TbSysUser tbSysUser = (TbSysUser) request.getSession().getAttribute("tbSysUser");
        String tbPostsPostJson = MapperUtils.obj2json(tbPostsPost);
        String json = postsService.save(tbPostsPostJson, tbSysUser.getUserCode());
        BaseResult baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        if(baseResult != null && baseResult.getStatus() == 200){
            return "redirect:/index";
        }
        return "redirect:/form";
    }

    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(){
        return "form";
    }

    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public DataTablesResult page(HttpServletRequest request){

        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 0 : Integer.parseInt(strLength);

        int pageNum = start / length + 1;

        String json = postsService.page(pageNum, length, null);
        DataTablesResult dataTablesResult = null;

        try{
            dataTablesResult = MapperUtils.json2pojo(json, DataTablesResult.class);
            dataTablesResult.setDraw(draw);
            dataTablesResult.setRecordsTotal(dataTablesResult.getCursor().getTotal());
            dataTablesResult.setRecordsFiltered(dataTablesResult.getCursor().getTotal());
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataTablesResult;
    }
}
