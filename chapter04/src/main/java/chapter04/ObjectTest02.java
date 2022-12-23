package chapter04;

public class ObjectTest02 {

	public static void main(String[] args) {
		Point p1 = new Point(10, 20);
		Point p2 = new Point(10, 20);
		Point p3 = p2;
		
		// == : 두 객체의 동일성 비교
		System.out.println(p1 == p2);
		System.out.println(p2 == p3);
		
		// equals : 두 객체의 동질성 비교, '==' 연산자랑 동일한 결과
		// 내장함수를 사용하는 사람이 어떠한 형태로 사용할지 모르니까 == 연산자와
		// 동일하게 만들어 놓음 -> 사용자가 오버라이딩 해야함!
		System.out.println(p1.equals(p2));
		System.out.println(p2.equals(p3));
	}

}
