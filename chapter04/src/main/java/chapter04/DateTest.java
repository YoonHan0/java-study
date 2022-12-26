package chapter04;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {
		Date now = new Date();
		
		System.out.println(now);
		printDate01(now);
		printDate02(now);
	}

	private static void printDate01(Date now) {
		//2022-12-26 13:01:06
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = sdf.format(now);
		
		System.out.println(date);
	}
	
	private static void printDate02(Date now) {
		// 년도(+1900)
		int year = now.getYear();
		
		// 월
		int month = now.getMonth();
		
		// 일
		int date = now.getDate();
		
		// 시간
		int hours = now.getHours();
		
		// 분
		int minutes = now.getMinutes();
		
		// 초
		int sec = now.getSeconds();
		
		System.out.println(
				(year+1900) + "-" +
				month + "-" +
				date + " " +
				hours + ":" +
				minutes + ":" +
				sec);
	}

}
