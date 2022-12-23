package prob6;
/* 직사각형 */
public class Rectangle extends Shape implements Resizable{

	private double width;
	private double height;
	
	public Rectangle(double width, double height) {
		super();
		this.width = width;
		this.height = height;
	}
	public void resize(double s) {
		this.width = this.width * s;
		this.height = this.height * s; 
	}
	
	/*넓이*/
	public double getArea() {
		return width * height;
	}
	
	/*둘레 길이*/
	public double getPerimeter() {
		return (width + height)*2;
	}
}
