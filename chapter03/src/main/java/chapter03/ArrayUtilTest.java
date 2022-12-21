package chapter03;

import java.util.Arrays;

public class ArrayUtilTest {

	public static void main(String[] args) {
//		int[] a = {10, 20, 30, 40};	// 배열의 리터널에 대한 블록임(코드 블록X)
//		int[] a = new int[4];
//		a[0] = 10;
//		a[1] = 20;
//		a[2] = 30;
//		a[3] = 40;

		// ArrayUtil 클래스에서 intToDouble 메소드의 로직 처리 결과를 넘겨받아 출력
		double[] d = ArrayUtil.intToDouble(new int[] {10, 20, 30, 40});
		System.out.println(Arrays.toString(d));
		
		int[] i1 = ArrayUtil.doubleToInt(new double[] {10.0, 11.1, 22.2, 33.3});
		System.out.println(Arrays.toString(i1));
		
		int[] c1 = ArrayUtil.concat(new int[] {10, 11, 22, 33}, new int[] {44, 55, 66, 77});
		System.out.println(Arrays.toString(c1));
		
//		Goods g = new Goods();	// 객체 선언
//		System.out.println(g);	// println으로 객체를 출력하면 toString()을 사용해서 출력한다는 것을 알 수 있다.
//		System.out.println(g.toString());
	}

}
