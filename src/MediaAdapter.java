
import java.io.File;


public class MediaAdapter implements MediaPlayer {

	private String type;
	
	
	public MediaAdapter(File fileName){
		if((fileName.getName().endsWith(".mp3")) || (fileName.getName().endsWith(".MP3"))){
		
    		type = "mp3";
    	}else if(fileName.getName().endsWith(".wav") || fileName.getName().endsWith(".WAV")){
    		type = "wav";
    	
    	}
	}
	
	public void play(File fileName) {
		if(type.equals("mp3") || type.equals("wav")){
			MediaPlayer.playMedia(fileName);
		}
	}

	public void stop(File fileName) {
		MediaPlayer.stopMedia(fileName);
	}

}
