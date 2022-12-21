package chapter03;

public class Song {
	private String title;
	private String album;
	private String composer;
	private String artist;
	private int track;
	private int year;
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setTrack(int track) {
		this.track = track;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	public void show() {
		System.out.println("제목: " + title);
		System.out.println("앨범: " + album);
		System.out.print("작곡가: " + composer + "\n"
				+ "가수: " + artist + "\n"
				+ "트랙: " + track + "\n"
				+ "년도: " + year);
	}
	
}
