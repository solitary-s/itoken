package com.aloneness.itoken.web.posts.service;

import com.aloneness.itoken.common.dto.BaseResult;
import com.aloneness.itoken.web.posts.service.fallback.PostsServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "itoken-service-posts", fallback = PostsServiceFallback.class)
@Service
public interface PostsService {

    @RequestMapping(value = "v1/posts/page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public String page(@PathVariable(required = true) int pageNum,
                       @PathVariable(required = true) int pageSize,
                       @RequestParam(required = false) String tbPostsPostJson);

    @RequestMapping(value = "v1/posts/{postGuid}", method = RequestMethod.GET)
    public String getPostById(@PathVariable(required = true) String postGuid);


    @RequestMapping(value = "v1/posts", method = RequestMethod.POST)
    public String save(
            @RequestParam(value = "tbPostsPostJson") String tbPostsPostJson,
            @RequestParam(value = "createBy") String createBy);

}
