package com.system.mapper;

import com.system.po.Userlogin;
import org.springframework.stereotype.Repository;

@Repository
public interface UserloginDao {
    Userlogin selectByUserlogin(Userlogin ul);

    void updatePassword(Userlogin userlogin);
}
