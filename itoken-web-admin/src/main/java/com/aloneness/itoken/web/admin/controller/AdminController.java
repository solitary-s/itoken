package com.aloneness.itoken.web.admin.controller;

import com.aloneness.itoken.common.domain.TbSysUser;
import com.aloneness.itoken.common.dto.BaseResult;
import com.aloneness.itoken.common.utils.MapperUtils;
import com.aloneness.itoken.web.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("v1/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ResponseBody
    public String page(@PathVariable(required = true) int pageNum,
                    @PathVariable(required = true) int pageSize,
                    @RequestParam(required = false) TbSysUser tbSysUser){
        BaseResult baseResult = adminService.page(pageNum, pageSize, tbSysUser);
        String json = null;
        try {
            json = MapperUtils.obj2json(baseResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
