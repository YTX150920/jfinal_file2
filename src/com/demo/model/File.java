package com.demo.model;

import com.demo.model.base.BaseFile;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class File extends BaseFile<File>{

	public static final File me = new File();
	public Page<File> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from file order by id asc");
	}
}
