package com.system.service.impl;


import com.system.mapper.UserloginDao;
import com.system.po.Userlogin;
import com.system.service.UserloginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Jacey on 2017/6/29.
 */
@Service
public class UserloginServiceImpl implements UserloginService {

    @Resource
    private UserloginDao userloginDao;

    @Override
    public Userlogin findByNameandpass(Userlogin userlogin) throws Exception {
        return userloginDao.selectByUserlogin(userlogin);
    }

    @Override
    public void passwordReset(Userlogin userlogin) {
        userloginDao.updatePassword(userlogin);
    }

}
