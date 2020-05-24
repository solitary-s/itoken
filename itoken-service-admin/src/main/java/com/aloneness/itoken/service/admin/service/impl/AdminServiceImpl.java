package com.aloneness.itoken.service.admin.service.impl;

import com.aloneness.itoken.common.domain.TbSysUser;
import com.aloneness.itoken.common.mapper.TbSysUserMapper;
import com.aloneness.itoken.common.service.impl.BaseServiceImpl;
import com.aloneness.itoken.service.admin.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AdminServiceImpl extends BaseServiceImpl<TbSysUser, TbSysUserMapper> implements AdminService<TbSysUser> {

}
