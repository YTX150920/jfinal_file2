package com.demo.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

public class FtpClientUtil {

	static Logger logger = Logger.getLogger(FtpClientUtil.class.getName());

	// 连接FTP服务器 (jdk1.7)
	public static FtpClient connectFTP(String url, int port, String username, String password) {

		FtpClient ftpClient = null;

		try {

			SocketAddress addr = new InetSocketAddress(url, port);

			ftpClient = FtpClient.create();
			ftpClient.connect(addr);

			ftpClient.login(username, password.toCharArray());
			ftpClient.setBinaryType();

			logger.info("\n登录ftp成功");
		} catch (FtpProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ftpClient;
	}

	// 关闭FTP连接
	public static void disconnectFTP(FtpClient ftpClient) {
		try {
			ftpClient.close();
			logger.info("\n关闭ftp成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 获取当前连接目录下所有文件列表
	@SuppressWarnings("deprecation")
	public static List<String> getFileNameList(FtpClient ftpClient, String path) {
		List<String> list = new ArrayList<String>();
		try {
			DataInputStream dis = new DataInputStream(ftpClient.nameList(path));
			String filename = "";
			while ((filename = dis.readLine()) != null) {
				list.add(filename);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 文件下载
	public static void download(FtpClient ftpClient, String ftpFile , String localFile) {
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			// 获取ftp上的文件
			is = ftpClient.getFileStream(ftpFile);
			File file = new File(localFile);
			byte[] bytes = new byte[1024];
			int i;
			fos = new FileOutputStream(file);
			while ((i = is.read(bytes)) != -1) {
				fos.write(bytes, 0, i);
			}
		} catch (FtpProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}