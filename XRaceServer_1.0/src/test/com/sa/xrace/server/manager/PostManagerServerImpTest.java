package test.com.sa.xrace.server.manager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import com.sa.xrace.server.manager.PostManagerServerImp;
import com.sa.xrace.server.pool.InforPoolServer;

import junit.framework.TestCase;

public class PostManagerServerImpTest extends TestCase{

	private static final String TAG = "-- PostManagerServerImp --";
	public static final byte LOGIN = 101;
	private InforPoolServer sPool;
	private DataInputStream sInput;
	private Socket socket;
	private ServerSocket serversocket;
	private int index;
	private DataOutputStream sOutput;	
	
	PostManagerServerImp pmsi;
	
	public void setUp() throws Exception{
		super.setUp();
		sPool =new  InforPoolServer();
		pmsi= new PostManagerServerImp(sPool);
		createClientSocket();
	}
	
	public void tearDown() throws Exception{
		
		socket.close();
		serversocket.close();
		super.tearDown();
		
		
	}
		
	public void createClientSocket()throws Exception{
		serversocket = new ServerSocket(1210);
		
		socket = new Socket(InetAddress.getLocalHost(), 1210);			
	}
	
	
	public void testSendChannelLoginReply(){
	  
        try{
		sPool.updateUserLogin("abc", socket, new DataOutputStream (socket.getOutputStream()));
		pmsi.sendChannelLoginReply();
		 Socket tsocket= serversocket.accept();
		DataInputStream dd=new  DataInputStream( tsocket.getInputStream());
		byte i=dd.readByte();
		int b=dd.readInt(); 
		byte bb = dd.readByte();
		//String ss = dd.readUTF();
		System.out.println(b);
		System.out.println(i);
		System.out.println(bb);
		System.out.println(sPool.getOneCarInformation(sPool.getNCarNumber() - 1).getNCarID());
	    //System.out.println(ss);
	    System.out.println(sPool.getLoginedNames());       
        }catch(Exception e){
        	
        	try{
        		socket.close();
    		    serversocket.close();
        	}catch(Exception ex){
        		ex.toString();
        	}
        }
       
	}
	
	public void testSendChannelLogoutReply(){
	  try{
		sPool.updateUserLogin("abc", socket, new DataOutputStream (socket.getOutputStream()));
		pmsi.sendChannelLogoutReply();
		Socket ssocket =serversocket.accept();
		DataInputStream ds = new DataInputStream(ssocket.getInputStream());
		byte i= ds.readByte();
		System.out.println(i);
	  }catch(Exception e){
		  try{
      		socket.close();
  		    serversocket.close();
      	}catch(Exception ex){
      		ex.toString();
      	}
	  }
	}
	
	public void testSendChannelCarType(){
		byte id=11;
		try{
		sPool.updateUserLogin("abc", socket, new DataOutputStream (socket.getOutputStream()));
		pmsi.sendChannelCarType(id);
		Socket ssocket =serversocket.accept();
		DataInputStream ds = new DataInputStream(ssocket.getInputStream());
		}catch(Exception e){
			try{
	      		socket.close();
	  		    serversocket.close();
	      	}catch(Exception ex){
	      		ex.toString();
	      	}
			
		}
	}
	
	public void testSendChannelStartReply(){
		try{
			sPool.updateUserLogin("abc", socket, new DataOutputStream (socket.getOutputStream()));
			pmsi.sendChannelStartReply();
			Socket ssocket =serversocket.accept();
			DataInputStream ds = new DataInputStream(ssocket.getInputStream());
			}catch(Exception e){
				try{
		      		socket.close();
		  		    serversocket.close();
		      	}catch(Exception ex){
		      		ex.toString();
		      	}
				
			}
	}
	
	public void testSendChannelNormal(){
		byte id =11;
		try{
			sPool.updateUserLogin("abc", socket, new DataOutputStream (socket.getOutputStream()));
			pmsi.sendChannelNormal(id);
			Socket ssocket =serversocket.accept();
			DataInputStream ds = new DataInputStream(ssocket.getInputStream());
			}catch(Exception e){
				try{
		      		socket.close();
		  		    serversocket.close();
		      	}catch(Exception ex){
		      		ex.toString();
		      	}
				
			}
	}
	
	public void testSendChannelAccidence(){
		byte id =11;
		try{
			sPool.updateUserLogin("abc", socket, new DataOutputStream (socket.getOutputStream()));
			pmsi.sendChannelAccidence(id);
			Socket ssocket =serversocket.accept();
			DataInputStream ds = new DataInputStream(ssocket.getInputStream());
			}catch(Exception e){
				try{
		      		socket.close();
		  		    serversocket.close();
		      	}catch(Exception ex){
		      		ex.toString();
		      	}
				
			}
	}
	
	public void testSendChannelIdle(){
		byte id =11;
		try{
			sPool.updateUserLogin("abc", socket, new DataOutputStream (socket.getOutputStream()));
			pmsi.sendChannelIdle(id);
			Socket ssocket =serversocket.accept();
			DataInputStream ds = new DataInputStream(ssocket.getInputStream());
			}catch(Exception e){
				try{
		      		socket.close();
		  		    serversocket.close();
		      	}catch(Exception ex){
		      		ex.toString();
		      	}
				
			}
	}
	
	public void testSendLogingFailue(){
		try{
			sPool.updateUserLogin("abc", socket, new DataOutputStream (socket.getOutputStream()));
			pmsi.sendLogingFailue(socket,new DataOutputStream (socket.getOutputStream()));
			Socket ssocket =serversocket.accept();
			DataInputStream ds = new DataInputStream(ssocket.getInputStream());
			}catch(Exception e){
				try{
		      		socket.close();
		  		    serversocket.close();
		      	}catch(Exception ex){
		      		ex.toString();
		      	}
				
			}
	}
}
