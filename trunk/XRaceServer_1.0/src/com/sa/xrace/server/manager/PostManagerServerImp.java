package com.sa.xrace.server.manager;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.sa.xrace.server.pool.InforPoolServer;

/**
 * @author Changpeng Pan
 * @version $Id: PostManagerServerImp.java,v 1.2 2008-11-26 05:47:58 cpan Exp $
 */
public class PostManagerServerImp implements PostManagerServer {
	private static final String TAG = "-- PostManagerServerImp --";
	private InforPoolServer sPool;
	private DataOutputStream sOutput;
	private int index;

	/**
	 * Reads an inforPoolServer to initiate the postManager
	 */
	public PostManagerServerImp(InforPoolServer pool) {
		sPool = pool;
	}

	/**
	 * Sends the posts to all the users as some one required a login Generates a
	 * PostTOClient Reads and int indicates the time costs, which server spend
	 * in processing the request
	 */
	public synchronized void sendChannelLoginReply() {
		for (int i = 0; i < sPool.getOutSVector().size(); i++) {
			try {
				sOutput = sPool.getOutSVector().get(i);
				sOutput.writeByte(InforPoolServer.LOGIN);
				sOutput.writeInt(sPool.getNCarNumber());
				sOutput.writeByte(sPool.getOneCarInformation(sPool.getNCarNumber() - 1).getNCarID());
				sOutput.writeUTF(sPool.getLoginedNames());
				for(int n=0;n<sPool.getNCarNumber();n++)
				{
					sOutput.writeInt(sPool.getOneCarInformation(n).getCarListName());//write car listname
				}
				sOutput.flush();
			} catch (IOException e) {
				System.out.println(TAG + "sendChannelLoginReply()");
			}
		}
		sPool.loginReplyProcess();
	}

	/**
	 * Sends the posts to all the users as some one required a logout Generates
	 * a PostTOClient
	 */
	public synchronized void sendChannelLogoutReply() {
		for (int i = 0; i < sPool.getOutSVector().size(); i++) {
			try {
				sOutput = sPool.getOutSVector().get(i);
				sOutput.writeByte(InforPoolServer.LOGOUT);
				sOutput.writeByte(sPool.getLogoutDropoutCarID());
				sOutput.writeInt(InforPoolServer.getOuterCarListName());
				sOutput.flush();
			} catch (IOException e) {
				System.out.println(TAG + "sendChannelLogoutReply()");
			}
		}
	}

	/**
	 * Sends the posts to all the users as some one required a logout Generates
	 * a PostTOClient
	 */
	public synchronized void sendChannelDropoutReply() {
		for (int i = 0; i < sPool.getOutSVector().size(); i++) {
			try {
				sOutput = sPool.getOutSVector().get(i);
				sOutput.writeByte(InforPoolServer.DROPOUT);
				sOutput.writeByte(sPool.getLogoutDropoutCarID());
				sOutput.writeInt(InforPoolServer.getOuterCarListName());
				sOutput.flush();
			} catch (IOException e) {
				System.out.println(TAG + "sendChannelDropoutReply()");
			}
		}
	}

	/**
	 * Sends the posts to all the users in the type of one car Generates a
	 * PostTOClient
	 */
	public void sendChannelCarType(byte id) {
		index = (int) id;
		for (int i = 0; i < sPool.getOutSVector().size(); i++) {
			try {
				sOutput = sPool.getOutSVector().get(i);
				sOutput.writeByte(InforPoolServer.CARTYPE);
				sOutput.writeByte(sPool.getOneCarInformation(index).getNCarID());
				sOutput.writeByte(sPool.getOneCarInformation(index).getMModelID());
				sOutput.flush();
			} catch (IOException e) {
				System.out.println(TAG + "sendChannelCarType()");
			}
		}
	}

	/**
	 * Sends the posts to all the users in start situation Generates a
	 * PostTOClient
	 */
	public synchronized void sendChannelStartReply() {

		for (int i = 0; i < sPool.getOutSVector().size(); i++) {
			try {
				sOutput = sPool.getOutSVector().get(i);
				sOutput.writeByte(InforPoolServer.START);
				sOutput.flush();
			} catch (IOException e) {
				System.out.println(TAG + "sendChannelStartReply()");
			}
		}

	}

	/**
	 * Sends the posts to all the users in normal situation Generates a
	 * PostTOClient Reads and int indicates the time costs, which server spend
	 * in processing the request
	 */
	public synchronized void sendChannelNormal(byte id) {
		index = (int) id;
		for (int i = 0; i < sPool.getOutSVector().size(); i++) {
			try {
				sOutput = sPool.getOutSVector().get(i);
				sOutput.writeByte(InforPoolServer.NORMAL);
				sOutput.writeByte(sPool.getOneCarInformation(index).getNCarID());
				sOutput.writeFloat(sPool.getOneCarInformation(index).getNSpeed());
				sOutput.writeFloat(sPool.getOneCarInformation(index).getNDirection());
				sOutput.writeFloat(sPool.getOneCarInformation(index).getNAcceleration());
				sOutput.writeFloat(sPool.getOneCarInformation(index).getNChangeDirection());
				sOutput.writeFloat(sPool.getOneCarInformation(index).getNXPosition());
				sOutput.writeFloat(sPool.getOneCarInformation(index).getNYPosition());
				sOutput.writeShort(sPool.getOneCarInformation(index).getTimeDelay());
				sOutput.flush();
			} catch (IOException e) {
				System.out.println(TAG + "sendChannelNormal()");
			}
		}
	}

	/**
	 * Sends the posts to all the users in accident situation Generates a
	 * PostTOClient Reads and int indicates the time costs, which server spend
	 * in processing the request
	 */
	public synchronized void sendChannelAccidence(byte id) {
		index = (int) id;
		for (int i = 0; i < sPool.getOutSVector().size(); i++) {
			try {
				sOutput = sPool.getOutSVector().get(i);
				sOutput.writeByte(InforPoolServer.ACCIDENT);
				sOutput.writeByte(sPool.getOneCarInformation(index).getNCarID());
				sOutput.writeFloat(sPool.getOneCarInformation(index).getNAccidenceSpeed());
				sOutput.writeFloat(sPool.getOneCarInformation(index).getNAccidenceDirection());
				sOutput.writeFloat(sPool.getOneCarInformation(index).getNAccidenceAcceleration());
				sOutput.writeFloat(sPool.getOneCarInformation(index).getNAccidenceChangeDirection());
				sOutput.writeFloat(sPool.getOneCarInformation(index).getNXPosition());
				sOutput.writeFloat(sPool.getOneCarInformation(index).getNYPosition());
				sOutput.writeFloat(sPool.getOneCarInformation(index).getTimeDelay());
			} catch (IOException e) {
				System.out.println(TAG + "sendChannelAccidence()");
			}
		}
	}

	/**
	 * Sends the posts to all the users in idle situation Generates a
	 * PostTOClient Reads and int indicates the time costs, which server spend
	 * in processing the request
	 */
	public synchronized void sendChannelIdle(byte id) {
		index = (int) id;
		for (int i = 0; i < sPool.getOutSVector().size(); i++) {
			try {
				sOutput = sPool.getOutSVector().get(i);
				sOutput.writeByte(InforPoolServer.IDLE);
				sOutput.writeByte(id);
				sOutput.writeShort(sPool.getOneCarInformation(index).getTimeDelay());
				sOutput.flush();
			} catch (IOException e) {
				System.out.println(TAG + "sendOutStremPostToAll()");
			}
		}
	}
	
	/**
	 * Sends the posts to the users who can't login
	 * When the Game has start or more the 
	 */
	public synchronized void sendLogingFailue(Socket socket,DataOutputStream output){
		try {
//			output = (DataOutputStream) socket.getOutputStream() ;
			output.writeByte(InforPoolServer.LOGINFAILURE);
			output.flush();
		} catch (IOException e) {
			System.out.println(TAG + "sendLogingFailue()");
		}
	}
	
	/**
	 * registers an output stream of a new user's socket into output stream
	 * vector
	 */
	public synchronized void registerOutStream(DataOutputStream input) {
		
		sPool.getOutSVector().add(input);
	}

	/**
	 * Removes an output stream of a user's socket from output stream vector
	 */
	public synchronized void removeOutStream(DataOutputStream input) {
		
		sPool.getOutSVector().remove(input);
	}


}
