package com.aloneness.itoken.web.admin.service.fallback;

import com.aloneness.itoken.common.domain.TbSysUser;
import com.aloneness.itoken.common.dto.BaseResult;
import com.aloneness.itoken.web.admin.service.AdminService;
import org.springframework.stereotype.Component;

@Component
public class AdminServiceFallback implements AdminService {

    @Override
    public BaseResult page(int pageNum, int pageSize, TbSysUser tbSysUser) {
        return null;
    }
}
