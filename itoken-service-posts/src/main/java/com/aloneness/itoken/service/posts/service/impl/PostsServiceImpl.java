package com.aloneness.itoken.service.posts.service.impl;

import com.aloneness.itoken.common.domain.TbPostsPost;
import com.aloneness.itoken.common.mapper.TbPostsPostMapper;
import com.aloneness.itoken.common.service.impl.BaseServiceImpl;
import com.aloneness.itoken.service.posts.service.PostsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PostsServiceImpl extends BaseServiceImpl<TbPostsPost, TbPostsPostMapper> implements PostsService<TbPostsPost> {

}
