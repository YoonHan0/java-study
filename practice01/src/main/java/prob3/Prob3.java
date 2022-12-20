package prob3;

import java.util.Scanner;

public class Prob3 {
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int result = 0;
		/* 코드 작성 */
		System.out.print("숫자를 입력하세요: ");
		int number = scanner.nextInt();
		
		if(number % 2 == 0) {	// 짝수일 때
			for(int i = 0; i <= number; i+=2) {
				result += i;
			}
		}
		else {
			for(int i = 1; i <= number; i+=2) {
				result += i;
			}
		}
		System.out.println("결과 값 : " + result);
		
		scanner.close();
	}
}
