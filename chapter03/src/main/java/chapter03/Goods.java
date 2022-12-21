package chapter03;

public class Goods {
	public static int countOfGoods = 10;
	
	private String name;
	private int price;
	private int countStock;
	private int countSold;
	
	public Goods() {
		countOfGoods += 1; 
		// Goods.countOfGoods += 1; // 생략 가능
	}
	public int calcDiscountPrice(float discountRate) {
		// int i = (int)4.5;
		return (int)(price * discountRate);
	}
	
	public void printInfo() {	// 필드 값을 출력하는 메소드
		System.out.println(name + ":" + price + ":" + countStock + ":" + countSold);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {	// 필드가 private형식이니까 setter를 이용해 다른 class에서 값을 할당할 수 있도록
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		if(price < 0) {
			price = 0;
		}
		this.price = price;
	}
	public int getCountStock() {
		return countStock;
	}
	public void setCountStock(int countStock) {
		this.countStock = countStock;
	}
	public int getCountSold() {
		return countSold;
	}
	public void setCountSold(int countSold) {
		this.countSold = countSold;
	}
	
	// 값을 가지고 오는 getter
	// 값을 세팅하는 setter
}
