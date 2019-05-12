import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;


public class MonitorImplSocket {
private ServerSocket serversocket;
private static Moniterinterface monitor;
private int portNo = 4070;

private final String FOLDERPATH = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "SharedFolder";

private File folder;
private boolean isChanged;
private static final String String = null;

public ArrayList<Observer> clientList;

public ArrayList<FileType> fileList;
private Socket clientsocket;
private final static int MAX_USERS = 0;
private FileType type;
private static String watchedValue;
private Timer timer;
private Hashtable<String, TimerTask> timerTasks;
private PrintWriter out;
private BufferedReader in;


	private MonitorImplSocket() {
		 this.folder = new File(this.FOLDERPATH);
	        clientList = new ArrayList<>();
	        fileList = new ArrayList<FileType>();
	        timer = new Timer(true);
	        timerTasks = new Hashtable<String, TimerTask>();
	        
	        this.fileList = new ArrayList<>();
	        if (!this.folder.exists()) {
	            this.folder.mkdir();
	        }
	        else{
	            System.out.println("Already Exists");

	        }
	        populateArray();    
	        
	        startConnection();
	}
	
    public void populateArray() {
        File[] array = this.folder.listFiles();
     
        this.fileList.clear();

        for(File s : array){

        	 FileType fileInfo = new FileType(s.getName(),s.getPath(),s.length()/1024.0);
            this.fileList.add(fileInfo);
        }
        System.out.print(fileList);
    }

	private void startConnection() {
		try {
			serversocket = new ServerSocket (portNo );
			System.out.println("Running on port No " + portNo);
			clientsocket = serversocket.accept();
		
//			in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
//			String request = in.readLine();
//			String param = in.readLine();
//			System.out.println(request + " - "+ param);
//			switch (request ) {
			
//			case "download":
//				this.download(clientsocket, param);
//				break;
			
//			}
			this.download(clientsocket, "song.mp3");
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

	public String[] getNames() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasFile(String name) {
		// TODO Auto-generated method stub
		return false;
	}


	public void download(Socket socket, String name) {
		
		File file = null;
		for (FileType f : this.fileList) {
			if(f.getName().equalsIgnoreCase(name)) {
				file = new File(f.getPath());
				break;
			}
		}
		
		if(file == null) {
			// handle error
		}
		
		 InputStream in = null;
			try {
				in = socket.getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //used

         BufferedInputStream bis = new BufferedInputStream(in);

         String dirPath  ;
         dirPath = "C:\\Users\\emmet\\Desktop\\SharedFolder";

         try{
             DataInputStream dis = new DataInputStream(bis);

             int filesCount = dis.readInt();
             System.out.println("Starting..."+filesCount);
             File[] files = new File[filesCount];
             long f_l = 0;
             int count =0 ;
             long totalBytesRead = 0;
             int percentCompleted = 0;

             for(int i = 0; i < filesCount; i++)
             {
                 long fileLength = dis.readLong();
                 System.out.println("Starting..."+fileLength);
                 String fileName = dis.readUTF();

                 f_l = f_l +fileLength;
                 files[i] = new File(dirPath + "/" + fileName);
                     System.out.println(fileName);
                 FileOutputStream fos = new FileOutputStream(files[i]);
                 BufferedOutputStream bos = new BufferedOutputStream(fos);

                 int tot = 0;
                 for(int j = 0; j < fileLength; j++) {

                     bos.write(bis.read());
                 }
                 System.out.println("Finished");

                 bos.close();

             } 

             socket.close(); 

         }catch(Exception ex)
         {
             System.out.println("error in socket programming ");
         }
	}


	public String share(String name, byte[] bytes) {
		// TODO Auto-generated method stub
		return null;
	}


	public void attach(Observer o) {
		// TODO Auto-generated method stub
		
	}


	public void deattach(Observer o) {
		// TODO Auto-generated method stub
		
	}
	
	 public static void main (String[] args) {
		 MonitorImplSocket monitor;
		 monitor = new MonitorImplSocket();
	 }
}


