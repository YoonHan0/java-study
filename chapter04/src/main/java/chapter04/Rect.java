package chapter04;

public class Rect {
	private int w;
	private int h;
	
	public Rect(int w, int h) {
		this.w = w;
		this.h = h;
		
	}
	
	@Override
	public String toString() {
		return "Point [w = " + w + "]" + " [h = " + h + "]";
	}
}
