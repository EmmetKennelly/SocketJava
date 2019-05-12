//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.IOException;
//import java.io.Writer;
//import java.util.ArrayList;
//import java.util.Observer;
//
//
//public abstract class  AbstractClient implements Observer {
//
//    private static int idTracker;
//    protected int id;
//    private MonitorImpl monitor;
//    private ArrayList<FileType> fileList;
//    private String name;
//
//
//  
//    public AbstractClient (MonitorImpl monitor) {
//        
//        this.monitor = monitor;
//       
//        
//        fileList  = monitor.fileList;
//        id = idTracker++;
//        
//       
//    }
//    	    
//    	    
//  
//    public void addFile(FileType fileName) {
//        this.monitor(fileName);
//    }
//
//  
//    public  FileType readFile(FileType file) throws InterruptedException {
//        String fileName = file.getName();
//        FileType res = null;
//  
//        
//        do{
//            res = monitor.getFile(file);
//            if (res == null){
//                int sleepTime = (int) (Math.random() * 10);
//                sleep(sleepTime);
//            }
//        }while (res == null);
//
//       
//        monitor.getFile(file).accuireAll();
//       
//        monitor.notifyAllClients();
//      
//        return res;
//    }
//
//    public void realeaseAFile(FileType file){
//        monitor.getFile(file).releasePermit();
//    }
//   
//    public void deleteFile(FileType file) {
//        String fileName = file.getName();
//        monitor.getFile(file).accuireAll();
//        monitor.deleteFile(file);
//        this.monitor.notifyAllClients();
//    }
//
//    public void update(int id, String action) {
//        System.out.println("Client:" + id +" has "+action+" a the mp3 file");
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//   
//    public String getName() {
//        String res = this.name;
//        return res;
//    }
//
// 
//    public void sleep(int time){
//
//        try {
//            Thread.sleep(time);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
