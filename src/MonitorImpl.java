


import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

import javax.management.monitor.Monitor;




import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;  
import java.net.Socket;  
  
public class MonitorImpl implements Moniterinterface {  
    public static void main(String[] args) {  
    	ServerSocket serverSocket = null;
    	System.out.println("Starting...");
		try {
			serverSocket = new ServerSocket(4070);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
        while(true) {  
            Socket clientSocket = null; 
            try {
				clientSocket = serverSocket.accept();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
           
       
            InputStream in = null;
			try {
				in = clientSocket.getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //used

            BufferedInputStream bis = new BufferedInputStream(in);

            String dirPath  ;
            dirPath = "C:\\Users\\emmet\\Desktop\\LocalFolder";

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
              serverSocket.close();
//              public boolean checkSharedFilesChanged(Socket clientSocket, ObjectInputStream ois, ObjectOutputStream oos) {
//
//                  boolean changed = false;
//
//                  try {
//                      oos.writeObject(2);
//                      changed = (boolean) ois.readObject();
//                  } catch (IOException | ClassNotFoundException e) {
//                      e.printStackTrace();
//                  }
//
//                  return changed;
//              }
//          
clientSocket.close(); 

            }catch(Exception ex)
            {
                System.out.println("error in socket programming ");
            }
          //  private boolean fileExists(File[] files, String filename) {
            //    boolean exists = false;
             //   for (File file : files) {
             //       if (filename.equals(file.getName())) {
             //           exists = true;
                    }
            }

	@Override
	public String[] getNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasFile(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public byte[] download(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String share(String name, byte[] bytes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void attach(Observer o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deattach(Observer o) {
		// TODO Auto-generated method stub
		
	}
        }
    


