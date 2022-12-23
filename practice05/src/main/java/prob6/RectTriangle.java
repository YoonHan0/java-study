package prob6;

/* 직각삼각형의 둘레 */
public class RectTriangle extends Shape {
	
	private double width;
	private double height;
	
	public RectTriangle(double width, double height) {
		super();
		this.width = width;
		this.height = height;
	}
	
	/*넓이*/
	public double getArea() {
		return (width * height) / 2;
	}
	
	/*둘레 길이*/
	public double getPerimeter() {
		return width + height + Math.sqrt(width*width + height*height);
	}
}

