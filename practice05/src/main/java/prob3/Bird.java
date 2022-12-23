package prob3;

public abstract class Bird {
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void fly() {
		System.out.println("날다.");
	}
	public void sing() {
		System.out.println("노래하다");
	}	
}