package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("> ");
		while(true) {
			String line = sc.nextLine();
			if(line.equals("exit")) {
				break;
			}
			// InetAddress -> 이 클래스는 생성자가 없음
			try {
				InetAddress[] addresses = InetAddress.getAllByName(line); 
				for(int i = 0; i < addresses.length; i++) {
					System.out.println(addresses[i].getHostName() + " : " + addresses[i].getHostAddress());
				}
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		sc.close();
	}
}
