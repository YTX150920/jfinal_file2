package com.demo.file;

import com.demo.model.File;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * BlogValidator.
 */
public class FileValidator extends Validator {
	
	protected void validate(Controller controller) {
		validateRequiredString("file.filename", "fileName", "请输入文件名!");
		validateRequiredString("file.filesize", "fileSize", "请输入文件大小!");
		validateRequiredString("file.filelines", "fileLines", "请输入文件行数!");
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
