package com.demo.user;

import com.demo.model.User;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * BlogValidator.
 */
public class UserValidator extends Validator {
	
	protected void validate(Controller controller) {
		validateRequiredString("user.username", "userName", "请输入用户名!");
		validateRequiredString("user.password", "password", "请输入密码!");
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(User.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/user/save"))
			controller.render("add.html");
		else if (actionKey.equals("/user/update"))
			controller.render("edit.html");
	}
}
