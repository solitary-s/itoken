package com.aloneness.itoken.service.posts.controller;

import com.aloneness.itoken.common.domain.TbPostsPost;
import com.aloneness.itoken.common.domain.TbSysUser;
import com.aloneness.itoken.common.dto.BaseResult;
import com.aloneness.itoken.common.utils.MapperUtils;
import com.aloneness.itoken.service.posts.service.PostsService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/posts")
public class PostsController {


    @Autowired
    private PostsService<TbPostsPost> postsService;

    @ApiOperation(value = "新增文章服务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tbPostsPostJson", value = "文章对象 JSON 字符串"),
            @ApiImplicitParam(name = "createBy", value = "创建者")
    })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public BaseResult savePost(@RequestParam(value = "tbPostsPostJson") String tbPostsPostJson,
                               @RequestParam(value = "createBy") String createBy){
        TbPostsPost tbPostsPost = null;
        if(StringUtils.isNotBlank(tbPostsPostJson)){
            try {
                tbPostsPost = MapperUtils.json2pojo(tbPostsPostJson, TbPostsPost.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(tbPostsPost != null){
            postsService.insert(tbPostsPost, createBy);
            return BaseResult.success();
        }
        return BaseResult.fail();
    }


    @ApiOperation(value = "根据Id获取文章服务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postGuid", value = "id", required = true, dataType = "String", paramType = "path"),
    })
    @RequestMapping(value = "{postGuid}", method = RequestMethod.GET)
    public BaseResult getPostById(@PathVariable(value = "postGuid") String postGuid){
        TbPostsPost post = new TbPostsPost();
        post.setPostGuid(postGuid);

        TbPostsPost tbPostsPost = postsService.selectOne(post);
        return BaseResult.success("success", tbPostsPost);
    }

    @ApiOperation(value = "文章分页查询服务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "笔数", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "tbPostsPostJson", value = "文章对象 JSON 字符串", required = false, dataTypeClass = String.class, paramType = "json")
    })
    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult page(@PathVariable(required = true) int pageNum,
                           @PathVariable(required = true) int pageSize,
                           @RequestParam(required = false) String tbPostsPostJson){
        TbPostsPost tbPostsPost = null;
        if(StringUtils.isNotBlank(tbPostsPostJson)){
            try {
                tbPostsPost = MapperUtils.json2pojo(tbPostsPostJson, TbPostsPost.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        PageInfo pageInfo = postsService.page(pageNum, pageSize, tbPostsPost);

        List<TbPostsPost> list = pageInfo.getList();

        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
        cursor.setOffset(pageInfo.getPageNum());
        cursor.setLimit(pageInfo.getPageSize());

        return BaseResult.success(list, cursor);
    }
}
