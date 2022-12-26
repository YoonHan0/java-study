package prob01;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GugudanApp {

	static int resultNumber = 0;

	public static void main(String[] args) {
		int l = randomize(1, 9);
		int r = randomize(1, 9);

		resultNumber = l * r;	// 정답

		int[] answerNumbers = randomizeAnswers(); // 빈 배열 9 리턴
		int loc = randomize(0, 8);					// 정답이 배치되는 위치
		
		answerNumbers[loc] = resultNumber;

		System.out.println(l + " x " + r + " = ?");
		int length = answerNumbers.length;
		for (int i = 0; i < length; i++) {
			if (i % 3 == 0) {
				System.out.print("\n");
			} else {
				System.out.print("\t");
			}

			System.out.print(answerNumbers[i]);
		}

		System.out.print("\n\n");
		System.out.print("answer:");

		Scanner s = new Scanner(System.in);
		int answer = s.nextInt();
		s.close();

		System.out.println((answer == resultNumber) ? "정답" : "오답");
	}

	private static int randomize(int lNum, int rNum) {
		int random = (int) (Math.random() * rNum) + lNum;
		return random;
	}

	private static int[] randomizeAnswers() {
		/* 코드 작성(수정 가능) */
		final int COUNT_ANSWER_NUMBER = 9;
		int[] boardNumbers = new int[COUNT_ANSWER_NUMBER]; // 길이9 배열
		Set<Integer> num = new HashSet<>();
		
		for(int i = 0; i < COUNT_ANSWER_NUMBER; i++) {			
			int lValue = (int) (Math.random()*9) + 1;
			int rValue = (int) (Math.random()*9) + 1;
			
			num.add(lValue * rValue);
		}
		
		while(num.size() == COUNT_ANSWER_NUMBER) {
			int lValue = (int) (Math.random()*9) + 1;
			int rValue = (int) (Math.random()*9) + 1;
			
			num.add(lValue * rValue);
		}
		Integer[] arr = num.toArray(new Integer[0]);	// HashSet to Integer[]
		boardNumbers = Arrays.stream(arr).mapToInt(Integer::intValue).toArray();	// Integer[] to int[]
		return boardNumbers;
	}
}
