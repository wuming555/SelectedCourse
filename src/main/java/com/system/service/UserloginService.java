package com.system.service;

import com.system.po.Userlogin;

/**
 *
 */
public interface UserloginService {

    //根据名字查找用户
    Userlogin findByNameandpass(Userlogin userlogin) throws Exception;

    void passwordReset(Userlogin userlogin);

}
