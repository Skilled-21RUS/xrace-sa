package com.sa.xrace.server.manager;

import java.io.DataOutputStream;
import java.net.Socket;


/**
 * @author Changpeng Pan
 * @version $Id: PostManagerServer.java,v 1.2 2008-11-26 05:47:58 cpan Exp $
 */
public interface PostManagerServer {
	
	public void sendChannelLoginReply();
	public void sendChannelLogoutReply();
	public void sendChannelCarType(byte id);
	public void sendChannelNormal(byte id);
	public void sendChannelAccidence(byte id);
	public void sendChannelIdle(byte id);
	public void sendChannelStartReply();
	public void sendChannelDropoutReply();
	public void registerOutStream(DataOutputStream input);
	public void removeOutStream(DataOutputStream input);
	public void sendLogingFailue(Socket socket,DataOutputStream input);
}
