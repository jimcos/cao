package com.cssl.service;

import java.sql.SQLException;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cssl.pojo.Users;
import com.cssl.vo.UsersVo;


public interface UsersService extends IService<Users> {

    public List<Users> findUsers();

}
