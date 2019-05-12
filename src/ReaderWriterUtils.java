import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReaderWriterUtils {
	
	//methods that are called in a few of the classes orginate here
	 public static void writeBytesToFile(File theFile, byte[] bytes) throws IOException {
	      BufferedOutputStream bos = null;
	      
	    try {
	      FileOutputStream fos = new FileOutputStream(theFile);
	      bos = new BufferedOutputStream(fos); 
	      bos.write(bytes);
	    }finally {
	      if(bos != null) {
	        try  {
	   
	          bos.flush();
	          bos.close();
	        } catch(Exception e){}
	      }
	    }
	    }
	
	
	static byte[] ReadtoBytes(File file) throws IOException{
		 InputStream is = new FileInputStream(file);
	     
	    
	     long length = file.length();
	 
	   
	     if (length > Integer.MAX_VALUE) {
	       throw new IOException("Could not completely read file " + file.getName() + " as it is too long (" + length + " bytes, max supported " + Integer.MAX_VALUE + ")");
	     }
	 
	  
	     byte[] bytes = new byte[(int)length];

	     int offset = 0;
	     int numRead = 0;
	     while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	         offset += numRead;
	     }

	     if (offset < bytes.length) {
	         throw new IOException("Could not completely read file " + file.getName());
	     }
	 
	     is.close();
	     return bytes;

		
	}
}

