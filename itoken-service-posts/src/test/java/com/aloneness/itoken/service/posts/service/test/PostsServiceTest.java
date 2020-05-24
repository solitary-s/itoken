package com.aloneness.itoken.service.posts.service.test;

import com.aloneness.itoken.common.domain.TbPostsPost;
import com.aloneness.itoken.common.domain.TbSysUser;
import com.aloneness.itoken.service.posts.ServicePostsApplication;
import com.aloneness.itoken.service.posts.service.PostsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServicePostsApplication.class)
//@Transactional
//@Rollback
public class PostsServiceTest {

    @Autowired
    private PostsService postsService;

    @Test
    public void testAdd() {

    }

    @Test
    public void testFind(){

    }

    @Test
    public void testFindPostById(){

    }
}