package tv;

public class TV {
	private int channel;	// 1 - 255 / 256->1, 257->1, -1 -> 1
	private int volume;		// 1 - 100
	private boolean power;

	public TV(int channel, int volume, boolean power) {
		this.channel = channel;
		this.volume = volume;
		this.power = power;
	}
	public void power(boolean on) {
		this.power = on;
	}
	public void ttt(int count, int tHis, int max) {
		if(count > max) {
			count = 1;
		}
		else if(count < 0) {
			count = max;
		}
		tHis = count;
	}
	public void channel(int channel) {
		ttt(channel, this.channel, 225);
	}
	
	public void volume(int volume) {
		ttt(volume, this.volume, 100);
	}
	
	public void channel(boolean up) {
		if(up) {
			this.channel += 1;
		}
		else {
			this.channel -= 1;
		}
		channel(this.channel);
	}
	
	public void volume(boolean up) {
		if(up) {
			this.volume += 1;
		}
		else {
			this.volume -= 1;
		}
		volume(this.volume);
	}
	
	public void status() {
		System.out.println(
				"TV의 전원 " + power + "\n" +
				"채널 " + channel + "\n" +
				"볼륨 " + volume);
	}
	
	
}
