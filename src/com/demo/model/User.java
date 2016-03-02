package com.demo.model;

import com.demo.model.base.BaseUser;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class User extends BaseUser<User>{

	public static final User me = new User();
	public Page<User> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from user order by id asc");
	}
}
