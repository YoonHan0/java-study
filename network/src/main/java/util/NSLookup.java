package util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NSLookup {

	public static void main(String[] args) {
		// 사용하는 메서드
		// String line = "www.douzone.com";  // 입력을 이렇게 받아라~
		try {
			InetAddress[] inetAddresses = InetAddress.getAllByName("www.douzone.com"); 
			for(InetAddress i : inetAddresses) {
				System.out.println(i);
			}
			// System.out.println(inetAddresses);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}	

	}

}
