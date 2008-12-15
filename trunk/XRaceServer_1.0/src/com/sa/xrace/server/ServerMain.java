package com.sa.xrace.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import com.sa.xrace.server.manager.PostManagerServer;
import com.sa.xrace.server.manager.PostManagerServerImp;
import com.sa.xrace.server.pool.InforPoolServer;

/**
 * @author yyang
 * @version $Id: ServerMain.java,v 1.2 2008-11-26 05:47:58 cpan Exp $
 */
public class ServerMain extends Thread {
	private static final String TAG = "-- ServerMain --";
	private static final int TIME_OUT = 500000;
	private ServerSocket sServerSocket;
	private Socket sSocket;
	private InforPoolServer sPool = new InforPoolServer();
	private PostManagerServer sPoster = new PostManagerServerImp(sPool);
	private Thread sThread;
	private static boolean sON;
	private ServerControlPanel sPanel;
	private int sPort;
	private DataInputStream sInput;
	private DataOutputStream sOutput;
	private byte sInputID;
	private byte sInputModelID;
	private String sInputName;
	private short sInputTimeDelay;
	
	private static boolean isBegin;

	/**
	 * Main process of this Server Reads and UI and port number
	 */
	public ServerMain(ServerControlPanel panel, int port) {
		this.sPort = port;
		this.sPanel = panel;
		sON = true;
		isBegin = false;
		start();
//		sPool.setNCarNumber(4);
	}

	/**
	 * return InforPoolServer
	 */
	public InforPoolServer getInforPoolServer() {
		return sPool;
	}

	/**
	 * Thread run()
	 */
	public void run() {
		try {
			System.out.println("-- Server Started --");
			sServerSocket = new ServerSocket(sPort, 6);
			while (sON) {
				sSocket = sServerSocket.accept();
				try {
					sInput = new DataInputStream(sSocket.getInputStream());
					sOutput = new DataOutputStream(sSocket.getOutputStream());
					sSocket.setSoTimeout(TIME_OUT);
				} catch (Exception e) {
					System.out.println(TAG + "outputStream creation error");
				}
				sThread = new ClientThread(sSocket, sInput, sOutput, sPoster);
				sThread.setPriority(Thread.MAX_PRIORITY);
				sThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(TAG + "run()");
		}
	}

	/**
	 * Server thread for the service of each user
	 */
	class ClientThread extends Thread {
		private Socket sCSocket;
		public String sSocketName;
		boolean isRun;
		public PostManagerServer sPoster;
		byte sPostReceived;
		DataInputStream sCInput;
		DataOutputStream sCOutput;

		/**
		 * Initiates a server thread for the service of each user
		 */
		public ClientThread(Socket socket, DataInputStream input,
				DataOutputStream output, PostManagerServer post) {
			this.sCSocket = socket;
			sPoster = post;
			sSocketName = new String();
			isRun = true;
			sCInput = input;
			sCOutput = output;
		}

		/**
		 * Ends the server thread and close the IOs
		 */
		public void EndThread() {
			try {
				isRun = false;
				sCInput.close();
				sCSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * Server thread run() Receives the posts from client Takes different
		 * actions depending on status of that post handles the user drop out
		 */
		public void run() {
			while (isRun) {
				try {
					sPostReceived = sCInput.readByte();
					if (sPostReceived == InforPoolServer.NORMAL) {
						sInputID = sCInput.readByte();
						sPool.updateUserCarNoamal(sInputID, sCInput.readFloat(), 
								sCInput.readFloat(), sCInput.readFloat(), 
								sCInput.readFloat(), sCInput.readFloat(),
								sCInput.readFloat(), sCInput.readShort());
						if (!sPool.getIsAccident()) {
							sPoster.sendChannelNormal(sInputID);
						} else {
							sPoster.sendChannelAccidence(sInputID);
						}
					} else if (sPostReceived == InforPoolServer.ACCIDENT) {
						
					} else if (sPostReceived == InforPoolServer.IDLE) {
						sInputID = sCInput.readByte();
						sInputTimeDelay = sCInput.readShort();
						sPool.updateUserCarIdle(sInputID, sInputTimeDelay);
						sPoster.sendChannelIdle(sInputID);
					} else if (sPostReceived == InforPoolServer.LOGIN) {
						if (sPool.getNCarNumber()>=4){
							sPoster.sendLogingFailue(sCSocket,sCOutput);
							System.out.println("--- sendLogingFailue  --");
						}else if(isBegin){
							sPoster.sendLogingFailue(sCSocket,sCOutput);
							System.out.println("--- sendLogingFailue  --");
						}else{
							sInputName = sCInput.readUTF();
							sSocketName = sInputName;
							sPanel.playerIn(sInputName);
							sPool.updateUserLogin(sInputName, sCSocket, sCOutput);
							sPoster.sendChannelLoginReply();
							InforPoolServer.LogOutListCar();	
						}
					} else if (sPostReceived == InforPoolServer.LOGOUT) {
						sInputID = sCInput.readByte();
						sInputName = sCInput.readUTF();
						sPanel.playerOut(sInputName, ServerControlPanel.LOGOUT);
						sPool.updateUserLogout(sInputID, sCSocket, sCOutput);
						sPoster.sendChannelLogoutReply();
						InforPoolServer.LogOutListCar();
						EndThread();
					} else if(sPostReceived == InforPoolServer.CARTYPE){
						sInputID = sCInput.readByte();
						sInputModelID = sCInput.readByte();
						sPool.updteUserCarType(sInputID, sInputModelID);
						sPoster.sendChannelCarType(sInputID);
					} else if (sPostReceived == InforPoolServer.START) {
						sPool.updateUserCarStart();
						sPoster.sendChannelStartReply();
//						GLThread_Room.setPhase(GLThread_Room.GAME_RUNNING);	
						isBegin = true;
					} else {
						System.out.println(TAG + "Invalid message!");
					}
				} catch (SocketTimeoutException e) {
					System.out.println("TimeoutException.......");
					sPanel.playerOut(sSocketName, ServerControlPanel.TIMEOUT);
					sPool.updateUserDropedOut(sCSocket, sCOutput);
					sPoster.sendChannelDropoutReply();
					InforPoolServer.LogOutListCar();
					EndThread();
				} catch (EOFException e) {
					System.out.println("EOFException....");
					sPanel.playerOut(sSocketName, ServerControlPanel.ERROR);
					sPool.updateUserDropedOut(sCSocket, sCOutput);
					System.out.println("EOFException");
					sPoster.sendChannelDropoutReply();
					InforPoolServer.LogOutListCar();
					EndThread();
				} catch (SocketException e) {
					System.out.println("SocketException....");
					sPanel.playerOut(sSocketName, ServerControlPanel.ERROR);
					sPool.updateUserDropedOut(sCSocket, sCOutput);
					System.out.println("SocketException");
					sPoster.sendChannelDropoutReply();
					InforPoolServer.LogOutListCar();
					EndThread();
				} catch (IOException e) {
					System.out.println("IO Exception....");
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println("Exception....");
					e.printStackTrace();
					sPool.updateUserDropedOut(sCSocket, sCOutput);
					sPoster.sendChannelDropoutReply();
					InforPoolServer.LogOutListCar();
					EndThread();
				}
			}

		}
	}
}
