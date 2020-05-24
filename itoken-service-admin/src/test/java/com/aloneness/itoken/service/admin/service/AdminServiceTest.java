package com.aloneness.itoken.service.admin.service;


import com.aloneness.itoken.common.domain.TbSysUser;
import com.aloneness.itoken.service.admin.ServiceAdminApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceAdminApplication.class)
//@Transactional
//@Rollback
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Test
    public void testRegister() {

    }

    /**
     * 登录
     */
    @Test
    public void testLogin() {

    }

}
