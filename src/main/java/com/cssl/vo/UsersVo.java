package com.cssl.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.io.Serializable;

@Data
public class UsersVo implements Serializable {

	@TableId
	private Integer id;		
	private String username;
	private String password;		
	
	
}
