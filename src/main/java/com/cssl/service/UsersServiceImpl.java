package com.cssl.service;

import java.sql.SQLException;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cssl.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cssl.dao.UsersDao;
import com.cssl.pojo.Users;


@Transactional
@Service
public class UsersServiceImpl extends ServiceImpl<UsersDao, Users> implements UsersService {



	@Autowired
	private RedisUtil util;



	@Override
	public List<Users> findUsers() {
		List<Users> list=null;
		if (util.exists("list")){
			System.out.println("exists....");
		}else{
			System.out.println("not exists");
			list=super.list();
		}

       return  list;
	}


}
