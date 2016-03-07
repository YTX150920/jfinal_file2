package com.demo.index;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;

import com.jfinal.core.Controller;

public class IndexController extends Controller {
	public void index() {
		render("index.html");
	}
	
	public void logout(){
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.isAuthenticated()){
			currentUser.logout();
		}
		redirect("/");
	}
	
	public void checkUser() {
		String username = getPara("username");
		String password = getPara("password");
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);

		try {
			currentUser.login(token);
			redirect("/file");
			
		} catch (Exception e) {
			//登录失败
			forwardAction("/");
		}
	}
}