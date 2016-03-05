package com.demo.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

//import org.apache.log4j.Logger;

import com.demo.io.FileOperation;

public class FileWriteThread extends Thread {
//	Logger logger = Logger.getLogger(this.getClass());

	private String sleepTime;

	public FileWriteThread() {

	}

	public FileWriteThread(String sleepTime) {
		super();
		this.sleepTime = sleepTime;
	}

	public void run() {
		int count = 0;
		long sleeptime = Long.parseLong(sleepTime);
		FileOperation fileOperation = new FileOperation();

		while (true) {
			// 获取当前时间字符串作为文件名
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
			String filePath = "f:\\Ftp\\";
			String fileName = sdf.format(date) + ".tmp";
			try {
				fileOperation.createNewFile(filePath, fileName);
				do {
					fileOperation.writeFile(filePath, fileName);
					count = fileOperation.readerFileByLines(filePath, fileName);

					Thread.sleep(sleeptime);
				} while (count < 10);
				{
					fileOperation.renameFile(filePath, fileName);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
