package com.demo.thread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

//import org.apache.log4j.Logger;

import com.demo.io.FileOperation;
import com.demo.model.File;
import com.demo.util.FtpClientUtil;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import sun.net.ftp.FtpClient;

public class FileReadTread extends Thread {
	Logger logger = Logger.getLogger(this.getClass());

	private String sleepTime;
	private String ip;
	private String port;
	private String username;
	private String password;

	public FileReadTread(String sleepTime, String ip, String port, String username, String password) {
		super();
		this.sleepTime = sleepTime;
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.password = password;

	}

	public void run() {
		String path = "";
		int port2 = Integer.parseInt(port);
		long sleeptime = Long.parseLong(sleepTime);
		List<String> list = new ArrayList<String>();
		List<Record> dbList = new ArrayList<Record>();
		List<String> dbNameList = new ArrayList<String>();

		FileOperation fileOperation = new FileOperation();
		FtpClient ftpClient = FtpClientUtil.connectFTP(ip, port2, username, password);

		while (true) {

			list = FtpClientUtil.getFileNameList(ftpClient, path);
			dbList = Db.find("select filename from file");

			for (int j = 0; j < dbList.size(); j++) {

				String dbFileName = String.valueOf(dbList.get(j)).replaceAll("(.)+?filename:", "")
						.replaceAll(", id(.+)?", "").replace("}", "");
				dbNameList.add(dbFileName);
			}

			// 去除重复的文件
			list.removeAll(dbNameList);

			for (int i = 0; i < list.size(); i++) {
				String fileName = list.get(i);
				Pattern pattern = Pattern.compile("[\\w]+\\.txt");
				Matcher matcher = pattern.matcher(fileName);

				if (matcher.matches()) {
					String fileSize = null;
					String fileLines = null;
					String localPath = "f://Download//" + fileName;
					FtpClientUtil.download(ftpClient, fileName, localPath);
					fileSize = fileOperation.getFileSize(localPath);
					try {
						fileLines = String.valueOf(fileOperation.readerFileByLines("f://Download//", fileName));
					} catch (IOException e) {
						e.printStackTrace();
					}

					// 存入数据库中
					File file = new File();
					file.setFilename(fileName);
					file.setFilesize(fileSize);
					file.setFilelines(fileLines);
					file.save();
					logger.info("存入数据库成功");
				}

				try {
					Thread.sleep(sleeptime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
