


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;  
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStream;  
import java.io.OutputStreamWriter;  
import java.net.Socket;  
import java.util.Scanner;  
  
public class SharedFolder {  
    public static void main(String[] args) throws Exception {  
    	Socket sock = new Socket("localhost", 4070);
        System.out.println("Connecting.........");
        
        
        String directory ="C:\\Users\\emmet\\Desktop\\SharedFolder";
      
        File[] files = new File(directory).listFiles();
        //File[] files = myFile.listFiles();
       OutputStream os = sock.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
                            DataOutputStream dos = new DataOutputStream(bos);

                            dos.writeInt(files.length);
                            long totalBytesRead = 0;
                            int percentCompleted = 0;
                            for(File file : files)
                            {
                                     long length = file.length();
                                     dos.writeLong(length);
                                        
                                     String name = file.getName();
                                     System.out.println(name);
                                     dos.writeUTF(name);

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
        sock.close();
        
    }

}
