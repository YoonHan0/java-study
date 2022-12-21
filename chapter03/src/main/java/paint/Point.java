package paint;

public class Point {
	private int x;
	private int y;
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void show() {
		System.out.println("점(x = " + x + ", y = " + y + ")을 그렸습니다!");
	}
	
	
}
