package prob02;

public class Goods {
	private String input;
	private String beverage;
	private int price;
	private int count;

	public Goods(String input) {
		this.input = input;
		spiltString(this.input);
	}

	
	public void show() {
		System.out.println(beverage + "(가격: " + price + "원)이 " + count + "개 입고 되었습니다.");
	}
	
	public void spiltString(String input) {
		String[] text = input.split(" ");
		this.beverage = text[0];
		this.price = Integer.parseInt(text[1]);
		this.count = Integer.parseInt(text[2]);
	}
}
