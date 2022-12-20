package prob01;

import java.util.Scanner;

public class Prob01 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner( System.in  );

		final int[] MONEYS = { 50000, 10000, 5000, 1000, 500, 100, 50, 10, 5, 1 };
		
		/* 코드 작성 */
		int[] count = new int[MONEYS.length];	// Moneys의 길이만큼의 크기 배열
		
		System.out.print("금액: ");
		int total = scanner.nextInt();	// 총 금액 입력받음
		System.out.print("\n");
		
		int temp = total;		// 전체 금액 temp
		for(int i = 0; i < MONEYS.length; i++) {
			count[i] = temp / MONEYS[i];	// 몫으로 개수 counting
			temp = temp % MONEYS[i];
			
			System.out.println(MONEYS[i] + "원 : " + count[i] + "개");
		}
		scanner.close();
 	}
}