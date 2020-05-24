package com.aloneness.itoken.service.admin.controller;

import com.aloneness.itoken.common.domain.TbSysUser;

import com.aloneness.itoken.common.dto.BaseResult;
import com.aloneness.itoken.common.utils.MapperUtils;
import com.aloneness.itoken.service.admin.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/admins")
public class AdminServiceController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult page(@PathVariable(required = true) int pageNum,
                           @PathVariable(required = true) int pageSize,
                           @RequestParam(required = false) String tbSysUserJson){
        TbSysUser tbSysUser = null;
        if(StringUtils.isNotBlank(tbSysUserJson)){
            try {
                tbSysUser = MapperUtils.json2pojo(tbSysUserJson, TbSysUser.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        PageInfo pageInfo = adminService.page(pageNum, pageSize, tbSysUser);

        List<TbSysUser> list = pageInfo.getList();

        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
        cursor.setOffset(pageInfo.getPageNum());
        cursor.setLimit(pageInfo.getPageSize());

        return BaseResult.success(list, cursor);
    }
}
