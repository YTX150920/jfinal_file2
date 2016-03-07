package com.demo.file;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;

import com.demo.model.File;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * BlogController
 * 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
@Before(FileInterceptor.class)
public class FileController extends Controller {
	
	@RequiresAuthentication
	public void index() {
		setAttr("filePage", File.me.paginate(getParaToInt(0, 1), 10));
		render("file.html");
	}
	
	@RequiresAuthentication
	public void add() {
	}
	
	@RequiresAuthentication
	@Before(FileValidator.class)
	public void save() {
		getModel(File.class).save();
		redirect("/file");
	}
	
	@RequiresAuthentication
	@RequiresRoles("admin")
	public void edit() {
		setAttr("file", File.me.findById(getParaToInt()));
	}
	
	@RequiresAuthentication
	@Before(FileValidator.class)
	public void update() {
		getModel(File.class).update();
		redirect("/file");
	}
	
	@RequiresAuthentication
	public void delete() {
		File.me.deleteById(getParaToInt());
		redirect("/file");
	}
	
	@RequiresAuthentication
	@RequiresRoles("admin")
	public void deleteSelected(){
		String idStr = getPara("idstr");
		int deleteId = 0;
		for(int i = 0 ; i < idStr.split(",").length ; i++){
			deleteId = Integer.parseInt(idStr.split(",")[i]);
			File.me.deleteById(deleteId);
		}
		redirect("/file");
	}
}


