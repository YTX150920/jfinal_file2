package com.demo.file;

import com.demo.model.File;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * BlogValidator.
 */
public class FileValidator extends Validator {
	
	protected void validate(Controller controller) {
		validateRequiredString("user.username", "userName", "请输入用户名!");
		validateRequiredString("user.password", "password", "请输入密码!");
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(File.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/file/save"))
			controller.render("add.html");
		else if (actionKey.equals("/file/update"))
			controller.render("edit.html");
	}
}
