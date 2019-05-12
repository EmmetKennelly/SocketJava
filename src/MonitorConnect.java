import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MonitorConnect implements Moniterinterface {
	private Socket clientsocket;
	private String Url ="localhost";
	private int port=4070;
	 private PrintWriter out;
	    private BufferedReader in;
	public MonitorConnect() {
		
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
		int current =0;
		 byte [] fileData = null;
			try {
				clientsocket = new Socket(Url, port);
				out = new PrintWriter(clientsocket.getOutputStream(), true);
				out.println("download");
				out.println(name);
				 
				String directory ="C:\\Users\\emmet\\Desktop\\SharedFolder";
			      
		        File[] files = new File(directory).listFiles();
		        //File[] files = myFile.listFiles();
		       OutputStream os = clientsocket.getOutputStream();
		        BufferedOutputStream bos = new BufferedOutputStream(os);
		                            DataOutputStream dos = new DataOutputStream(bos);

		                            dos.writeInt(files.length);
		                            long totalBytesRead = 0;
		                            int percentCompleted = 0;
		                            for(File file : files)
		                            {
		                                     long length = file.length();
		                                     dos.writeLong(length);
		                                        
		                                     String n = file.getName();
		                                     System.out.println(n);
		                                     dos.writeUTF(n);

		                                     FileInputStream fis = new FileInputStream(file);
		                                     BufferedInputStream bis = new BufferedInputStream(fis);

		                                     int theByte = 0;
		                                     while((theByte = bis.read()) != -1)
		                                     {
		                                        totalBytesRead += theByte;


		                                        bos.write(theByte);
		                                     }
		                                    //  System.out.println("file read");
		                                     bis.close();
		                                 }

		                                dos.close();


		        //Closing socket  
		        clientsocket.close();
		        
				
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					clientsocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return fileData;
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
