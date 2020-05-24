package com.aloneness.itoken.service.sso.service;

import com.aloneness.itoken.common.domain.BaseDomain;
import com.aloneness.itoken.common.domain.TbSysUser;
import com.aloneness.itoken.common.service.BaseService;
import com.aloneness.itoken.common.service.impl.BaseServiceImpl;

public interface LoginService<T extends BaseDomain> extends BaseService<T> {

    TbSysUser login(String loginCode, String plantPassword);

}
