package com.aloneness.itoken.service.sso.service.impl;

import com.aloneness.itoken.common.domain.TbSysUser;
import com.aloneness.itoken.common.mapper.TbSysUserMapper;
import com.aloneness.itoken.common.service.impl.BaseServiceImpl;
import com.aloneness.itoken.common.utils.MapperUtils;
import com.aloneness.itoken.service.sso.service.LoginService;
import com.aloneness.itoken.service.sso.service.consumer.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

@Service
public class LoginServiceImpl extends BaseServiceImpl<TbSysUser, TbSysUserMapper> implements LoginService<TbSysUser> {

    @Autowired
    private RedisService redisService;

    @Override
    public TbSysUser login(String loginCode, String plantPassword) {
        TbSysUser tbSysUser = null;
        String json = redisService.get(loginCode);

        if (json == null){

            Example example = new Example(TbSysUser.class);
            example.createCriteria().andEqualTo("loginCode", loginCode);

            tbSysUser = dao.selectOneByExample(example);
            if (tbSysUser != null) {
                String password = DigestUtils.md5DigestAsHex(plantPassword.getBytes());
                if (password.equals(tbSysUser.getPassword())) {
                    try {
                        redisService.put(loginCode, MapperUtils.obj2json(tbSysUser), 60*60*24);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return tbSysUser;
                }
                else {
                    return null;
                }
            }

        }

        else {

            try {
                tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return tbSysUser;
    }
}
