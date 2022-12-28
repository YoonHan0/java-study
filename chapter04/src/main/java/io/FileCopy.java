package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopy {

	public static void main(String[] args) {
		for(String arg : args) {
			System.out.println(arg);
		}
		
		InputStream is = null;
		OutputStream os = null;
		
		try {
			is = new FileInputStream("123.png");
			os = new FileOutputStream("123.copy.png");
			
			int data = -1;
			// 입력 스트림으로부터 더 이상 바이트를 읽을 수 없으면 read()는 -1 리턴 
			while((data = is.read()) != -1) { 
				os.write(data);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("file not found:" + e);
		} catch (IOException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(is != null) {
					is.close();
				}
				
				if(os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}