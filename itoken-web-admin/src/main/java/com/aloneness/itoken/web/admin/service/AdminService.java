package com.aloneness.itoken.web.admin.service;

import com.aloneness.itoken.common.domain.TbSysUser;
import com.aloneness.itoken.common.dto.BaseResult;
import com.aloneness.itoken.web.admin.service.fallback.AdminServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "itoken-service-admin", fallback = AdminServiceFallback.class)
@Service
public interface AdminService {

    @RequestMapping(value = "v1/admins/page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    BaseResult page(@PathVariable(required = true) int pageNum,
                    @PathVariable(required = true) int pageSize,
                    @RequestParam(required = false) TbSysUser tbSysUser);
}
