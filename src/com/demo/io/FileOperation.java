package com.demo.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileOperation {

	// 创建以时间字符串为文件名的*.tmp文件
	public String createNewFile(String filePath, String fileName) throws IOException {
		// 创建文件
		File file = new File(filePath + fileName);
		file.createNewFile();
		return fileName;
	}

	// 向*.tmp文件中写入内容
	public void writeFile(String filePath, String fileName) throws IOException {

		BufferedWriter out = null;
		String file = filePath + fileName;
		out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
		out.write("lllllllll" + "\r\n");
		out.close();
	}

	// 读取*.tmp文件,并返回行数
	public int readerFileByLines(String filePath, String fileName) throws IOException {

		int count = 0;
		File file = new File(filePath + fileName);
		InputStream input = new FileInputStream(file);
		BufferedReader b = new BufferedReader(new InputStreamReader(input));
		String value = b.readLine();
		if (value != null)
			while (value != null) {
				count++;
				value = b.readLine();
			}
		b.close();
		input.close();

		return count;
	}

	// 对符合要求的文件重命名
	public void renameFile(String filePath, String fileName) {
		File file = new File(filePath + fileName);
		if (file.exists()) {
			file.renameTo(new File(filePath + fileName.split("\\.")[0] + ".txt"));
		}
	}

	// 获取文件大小
	public String getFileSize(String path) {

		FileInputStream fis = null;
		String fileSize = null;
		try {
			File f = new File(path);
			fis = new FileInputStream(f);
			fileSize = String.valueOf(fis.available());
		} catch (Exception e) {
		} finally {
			if (null != fis) {
				try {
					fis.close();
				} catch (IOException e) {
				}
			}
		}

		return fileSize;

	}

}
