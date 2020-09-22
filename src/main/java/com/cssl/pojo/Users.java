package com.cssl.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Users {

	@TableId
	private Integer id;
	private String username;
	private String password;
	
}
