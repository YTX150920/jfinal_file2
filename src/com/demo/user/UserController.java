package com.demo.user;

import com.demo.user.UserInterceptor;
import com.demo.user.UserValidator;
import com.demo.model.User;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * BlogController
 * 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
@Before(UserInterceptor.class)
public class UserController extends Controller {
	public void index() {
		setAttr("userPage", User.me.paginate(getParaToInt(0, 1), 10));
		render("user.html");
	}
	
	public void add() {
	}
	
	@Before(UserValidator.class)
	public void save() {
		getModel(User.class).save();
		redirect("/user");
	}
	
	public void edit() {
		setAttr("user", User.me.findById(getParaToInt()));
	}
	
	@Before(UserValidator.class)
	public void update() {
		getModel(User.class).update();
		redirect("/user");
	}
	
	public void delete() {
		User.me.deleteById(getParaToInt());
		redirect("/user");
	}
}


