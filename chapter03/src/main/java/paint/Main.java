package paint;

public class Main {

	public static void main(String[] args) {
//		Point point = new Point();
		Point point1 = new Point(10, 10);
		
//		point.setX(10);
//		point.setY(10);
		
		drawPoint(point1);
		point1.show(false);
		
		Point point2 = new ColorPoint(20, 20, "red");
		point2.setX(20);
		point2.setY(20);
		//((ColorPoint)point2).setColor("red");
		//point2.setColor("red");
		
		drawPoint(point2);
		
		Rect rect = new Rect();
//		drawTriangle(triangle);
		drawShape(rect);
		
		Triangle triangle = new Triangle();
		drawShape(triangle);
//		drawTriangle(triangle);
		
		Circle circle = new Circle();
		drawShape(circle);
	}
	
	
	
	public static void drawPoint(Point point) {
		point.show();
	}
//	public static void drawColorPoint(ColorPoint colorpoint) {
//		colorpoint.show();
//	}
	public static void drawShape(Shape shape) {
		shape.draw();
	}
	
//	public static void drawRect(Rect rect) {
//		rect.draw();
//	}
//	public static void drawTriangle(Triangle triangle) {
//		triangle.draw();
//	}
//	public static void drawCircle(Circle circle) {
//		Circle.draw();
//	}
}
