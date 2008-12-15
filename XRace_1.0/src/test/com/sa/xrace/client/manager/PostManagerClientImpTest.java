package test.com.sa.xrace.client.manager;


import junit.framework.TestCase;

/**
 * 
 * @author jianwenhuang
 *
 */

public class PostManagerClientImpTest extends TestCase {

//	private static final long serialVersionUID = 1L;
//
//	private PostManagerClientImp postManagerClientImp;
//	private ServerSocket serverSocket = null;
//	private Socket tSocket = null;
//	private PostToServer postToServer = null;
//
//	protected void setUp()throws Exception{
//		createSocket();
//		initPostManagerClientImp();
//		super.setUp();
//	}
//	protected void tearDown()throws Exception{
//		closeSocket();
//		super.tearDown();
//	}
//	
//
//	public void testSendLoginPostToServer() {
//		postManagerClientImp.sendLoginPostToServer();
//		postToServer = getInputStream(serverSocket);
//		assertNotNull(postToServer);
//		assertEquals(InforPoolClient.LOGIN, postToServer.status);
//	}
//
//	public void testSendLogoutPostToServer() {
//		postManagerClientImp.sendLogoutPostToServer();
//		postToServer = getInputStream(serverSocket);
//		assertNotNull(postToServer);
//		assertEquals(InforPoolClient.LOGOUT, postToServer.status);
//
//	}
//
//	public void testSendStartPostToServer() {
//		postManagerClientImp.sendStartPostToServer();
//		postToServer = getInputStream(serverSocket);
//		assertNotNull(postToServer);
//		assertEquals(InforPoolClient.START, postToServer.status);
//	}
//
//	public void testSendIdlePostToServer() {
//		postManagerClientImp.sendIdlePostToServer();
//		postToServer = getInputStream(serverSocket);
//		assertNotNull(postToServer);
//		assertEquals(InforPoolClient.IDLE, postToServer.status);
//	}
//
//	public void testSendNormalPostToServer() {
//		postManagerClientImp.sendNormalPostToServer();
//		postToServer = getInputStream(serverSocket);
//		assertNotNull(postToServer);
//		assertEquals(InforPoolClient.NORMAL, postToServer.status);
//	}
//
//	/*
//	 * init PostManagerClientImp
//	 */
//	private void initPostManagerClientImp() {
//		InforPoolClient tinforpool = new InforPoolClient();
//		tinforpool.setMyCarIndex(0);
//		tinforpool.setNCarNumber(0);
//		
//		postManagerClientImp = new PostManagerClientImp(tSocket, tinforpool);
//	}
//
//	private void createSocket() {
//		try {
//			serverSocket = new ServerSocket(234);
//			tSocket = new Socket(InetAddress.getLocalHost(), 234);
//		} catch (Exception e) {
//			System.out.println("Exception:  " + e.toString());
//		}
//	}
//
//	private void closeSocket() {
//		if (serverSocket != null && tSocket != null) {
//			try {
//				tSocket.close();
//				serverSocket.close();
//			} catch (Exception e) {
//
//			}
//		}
//	}
//
//	private PostToServer getInputStream(ServerSocket socket) {
//		try {
//			return (PostToServer) (new ObjectInputStream(socket.accept()
//					.getInputStream())).readObject();
//		} catch (Exception ie) {
//			System.out.println("getInputStream occur Exception : "
//					+ ie.getMessage());
//			return null;
//		}
//	}

}
