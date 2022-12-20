package prob05;

import java.util.Random;
import java.util.Scanner;

public class Prob05 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner( System.in );
		int count = 1;
		int min = 1, max = 100;
		System.out.println("수를 결정하였습니다. 맞추어 보세요");
		System.out.println("Test");
		Random random = new Random();
		int correctNumber = random.nextInt( 100 ) + 1;
		System.out.println(correctNumber);
		
		while( true ) {
			System.out.println(min + "-" + max);
			System.out.print(count + ">>");
			int number = scanner.nextInt();
			
			if(number == correctNumber) {	// 맞추면
				System.out.println("맞았습니다.");
				
				System.out.print( "다시 하겠습니까(y/n)>>" );
				String answer = scanner.next();
				if( "y".equals( answer ) == false ) {
					break;
				}
				random = new Random();	// 새로운 난수 발생
				correctNumber = random.nextInt( 100 ) + 1;
				System.out.println(correctNumber);
				count = 1;
				min = 1;
				max = 100;
			}
			else if(number < correctNumber) {
				System.out.println("더 높게");
				min = number;
				++count;
			}
			else if(number > correctNumber){
				System.out.println("더 낮게");
				max = number;
				++count;
			}
		}
		
		scanner.close();
	}

}