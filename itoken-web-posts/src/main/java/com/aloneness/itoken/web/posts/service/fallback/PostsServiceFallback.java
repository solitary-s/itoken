package com.aloneness.itoken.web.posts.service.fallback;

import com.aloneness.itoken.common.dto.BaseResult;
import com.aloneness.itoken.common.utils.Fallback;
import com.aloneness.itoken.web.posts.service.PostsService;
import org.springframework.stereotype.Component;

@Component
public class PostsServiceFallback implements PostsService {
    @Override
    public String page(int pageNum, int pageSize, String tbPostsPostJson) {
        return Fallback.badGateway();
    }

    @Override
    public String getPostById(String postGuid) {
        return Fallback.badGateway();
    }

    @Override
    public String save(String tbPostsPostJson, String createBy) {
        return Fallback.badGateway();
    }
}
