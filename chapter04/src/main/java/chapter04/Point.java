package chapter04;

public class Point {
	private int x;
	private int y;
	
	public Point() {

	}
	public Point(int x, int y) {
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Point [x=]" + x + ", [y=" + y + "]";
	}
	
}
