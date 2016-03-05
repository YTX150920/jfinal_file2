package com.demo.test;

import java.util.ArrayList;
import java.util.List;

import com.demo.io.FileOperation;
import com.demo.thread.FileReadTread;
import com.demo.util.FtpClientUtil;

import sun.net.ftp.FtpClient;

public class Test {
	public static void main(String[] args) {

		int port = 21;
		String ip = "192.168.31.1";
		String username = "admin";
		String password = "admin";
		String path = "/";
//		List<String> list = new ArrayList<String>();

		// 连接ftp
//		FtpClient ftpClient = FtpClientUtil.connectFTP(ip, port, username, password);
		
//		FtpClientUtil.download(ftpClient, "20160304_140716.txt", "f://Download//20160304_140716.txt");

//		list = FtpClientUtil.getFileNameList(ftpClient, path);
//
//		for (int i = 0; i < list.size(); i++) {
//			list.get(i);
//			System.out.println(list.get(i));
//		}

		// 关闭ftp
		// FtpClientUtil.disconnectFTP(ftpClient);
//		FileOperation fileOperation = new FileOperation();
//		fileOperation.getFileSize("f://Download//20160304_140716.txt");
//		fileOperation.getFileSize("f://transwarp//gradle-2.11-all.zip");
//		fileOperation.getFileSize("f://Download//20160304_140716.txt");
		
		FileReadTread fileReadThread = new FileReadTread("1000",ip,"21",username,password);
		fileReadThread.start();
		
	}
}