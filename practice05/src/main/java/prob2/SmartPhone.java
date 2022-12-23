package prob2;

public class SmartPhone extends MusicPhone {

	@Override
	public void execute( String function ) {
		if (function.equals("음악") ) {
			playMusicSmartPhone();
	          return;
	      }
		else if(function.equals("앱")){
			playAppSmartPhone();
			return;
		}
	      
	      super.execute( function );	// if조건에 들어가지 않아서 return이 안되면 부모클래스의 execute 실행
	}
	
	private void playMusicSmartPhone() {
		System.out.println("다운로드해서 음악재생");
	}
	
	private void playAppSmartPhone() {
		System.out.print("앱실행");
	}
}
