package prob02;

public class Book {

	private int bookNum;
	private String title;
	private String author;
	private int stateCode;
	
	public Book(int bookNum, String title, String author) {
		this.bookNum = bookNum;
		this.title = title;
		this.author = author;
		this.stateCode = 1;		// 재고있음 상태코드 : 1
	}
	
	public void rent(int bookNum) {
		System.out.println("'" + title +  "'" + "이(가) 대여 됐습니다!");
		this.stateCode = 0;
	}
	
//	public void setStateCode(int stateCode) {
//		this.stateCode = stateCode;
//	}
	
	public int getStateCode() {
		return stateCode;
	}
	
	public int getBookNum() {
		return bookNum;
	}

	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	
}
