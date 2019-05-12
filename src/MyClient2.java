import java.io.File;
import java.io.IOException;

public class MyClient2 extends Observer {

	
	private GUI gui;
	private Moniterinterface monitor = null;
	private static final String LOCAL_STORAGE = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "LocalFolder";;
	//calling Local file location

	public MyClient2(GUI gui){
		this.gui = gui;
		this.monitor = new MonitorConnect();
		//this.monitor.attach(this);
	//attaching the client to the GUI
	}
	
	public String [] getSongs() {
		String[] array = new String[2];
		array[0] ="song.mp3";
		array[1] ="song2.mp3";
		return array;
		//return monitor.getNames();
	}
	
	public void downloadSong(String name) {
		byte[] soundbytes =monitor.download(name);
		File file = new File (LOCAL_STORAGE + File.separator + name);
		//byte array used to locate and download the desired file
		try {
			ReaderWriterUtils.writeBytesToFile(file, soundbytes);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void share(File file) {
		try {
			String name = file.getName();
			byte [] fileBytes = ReaderWriterUtils.ReadtoBytes(file);
			//byte array to read the bytes and give back a response throught the gui
			String response = monitor.share(name, fileBytes);
			System.out.println(response);
			
		} catch (IOException e) {
	
			e.printStackTrace();
		}
		
	}
	
	
	
	@Override
	public void update() {

		System.out.print("Got Update command");
		
		gui.setSongNames(monitor.getNames());
	}

}

