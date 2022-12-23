package prob4;

public abstract class Animal implements Soundable {
	private String cry;
	
	public void setCry(String cry) {
		this.cry = cry;
	}

	public String sound() {
		return cry;
	}
}
