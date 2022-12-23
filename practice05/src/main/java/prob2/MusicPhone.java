package prob2;

//변경불가
public class MusicPhone extends Phone {
	
	@Override
	public void execute ( String function ) {
	      if (function.equals("음악") ) {
	          playMusic();
	          return;
	      }
	      
	      super.execute( function );	// 무슨 말이지
	}

	private void playMusic() {
		System.out.print("MP3 플레이어에서 음악재생");
	}

}
