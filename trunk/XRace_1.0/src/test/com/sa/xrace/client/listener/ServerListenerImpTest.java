package test.com.sa.xrace.client.listener;

/**
 * 
 * @author jianwenhuang
 *
 */
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.sa.xrace.client.listener.ServerListenerImp;
import com.sa.xrace.client.pool.InforPoolClient;

public class ServerListenerImpTest {

	private ServerListenerImp sli;
	private Socket socket;
	private ServerSocket serversocket;
	private InforPoolClient inPool;

	public void setUp() throws Exception {
		inPool = new InforPoolClient();
		createSocket();
	}

	public void tearDown() throws Exception {
		closeSocket();
	}

	public void testInitServerListenerImp() throws Exception {
		sendData(socket);
		//sli = new ServerListenerImp(serversocket.accept(), inPool);
	//	System.out.print(sli.pool.getOneCarInformation(0).getNSpeed());
	}

	private void createSocket() {

		try {
			serversocket = new ServerSocket(1345);
			socket = new Socket(InetAddress.getLocalHost(), 1345);

		} catch (Exception e) {
			e.toString();
		}
	}

	private void closeSocket() {
		if (serversocket != null && socket != null) {
			try {
				socket.close();
				serversocket.close();
			} catch (Exception e) {

			}
		}
	}

	private void sendData(Socket asocket) {
		try {
			DataOutputStream dos = (new DataOutputStream(asocket
					.getOutputStream()));
			dos.writeByte(InforPoolClient.ACCIDENT);
			dos.writeByte(7);
			dos.writeFloat(10f);
			dos.writeFloat(11f);
			dos.writeFloat(12f);
			dos.writeFloat(13f);
			dos.writeFloat(14f);
			dos.writeFloat(15f);
			dos.writeShort(16);
			dos.flush();
			InforPoolClient.delayHandleOutADD(System.currentTimeMillis());
			// dos.close();
		} catch (Exception ie) {
			ie.printStackTrace();
		}
	}

	public static void main(String[] arg) {
		System.out.println("start");
		ServerListenerImpTest si = new ServerListenerImpTest();
		try {
			si.setUp();
			si.testInitServerListenerImp();
			si.tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				si.tearDown();
			} catch (Exception e) {

			}
		}

	}
}
