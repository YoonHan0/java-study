package paint;

public class Point {
	private int x;
	private int y;
	
	public Point(int x, int y) {	// 생성자를 만들어서 this로 값 초기화
		// this(x, y);	// 이게 왜 안됨?
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void show() {
		System.out.println("점(x = " + x + ", y = " + y + ")을 그렸습니다!");
	}
	
	// 오버로드
	public void show(boolean visible) {
		if(visible) {
			// System.out.println("점(x = " + x + ", y = " + y + ")을 그렸습니다!");
			show();	// 중복을 피하기 위해 show() 함수를 재사용!
		}
		else {
			System.out.println("점(x = " + x + ", y = " + y + ")을 지웠습니다!");
		}
	}
	
	
	
}
