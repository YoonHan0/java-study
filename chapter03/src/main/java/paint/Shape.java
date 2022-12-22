package paint;

public abstract class Shape {
	private String lineColor;
	private String fillColor;
	
	
	public void draw() {
		System.out.println("그림그리기");
	}
	public String getLineColor() {
		return lineColor;
	}
	public void setLineColor(String lineColor) {
		this.lineColor = lineColor;
	}
	public String getFillColor() {
		return fillColor;
	}
	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}
}
