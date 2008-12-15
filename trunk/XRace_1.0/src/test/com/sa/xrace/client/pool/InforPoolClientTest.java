package test.com.sa.xrace.client.pool;

import junit.framework.TestCase;

import com.sa.xrace.client.pool.InforPoolClient;

/**
 * @author Changpeng Pan
 * @version $Id: InforPoolClientTest.java,v 1.2 2008-12-10 05:12:02 cpan Exp $
 */
public class InforPoolClientTest extends TestCase {
	public InforPoolClientTest(String name) {
		super(name);
	}

	public void testSetMethod() {
		InforPoolClient mPool = new InforPoolClient();

		mPool.setMyCarIndex(5);
		assertEquals(5, mPool.getMyCarIndex());

		mPool.setNCarNumber(1);
		assertEquals(1, mPool.getNCarNumber());

	}

	public void testGetMethod() {
		InforPoolClient mPool2 = new InforPoolClient();
		assertEquals(0, mPool2.getMyCarIndex());
		assertEquals(0, mPool2.getNCarNumber());
	}

//	public void testGetOneCarInformation() {
//		InforPoolClient mPool3 = new InforPoolClient();
//		CarInforClient mCar = new CarInforClient();
//		mCar.setNAcceleration(0.0f);
//		mCar.setNAccidenceAcceleration(0.0f);
//		mCar.setNAccidenceDirection(0.0f);
//		mCar.setNAccidenceSpeed(0.0f);
//		mCar.setNCarID(0);
//		mCar.setNCarType(0);
//		mCar.setNDirection(0.0f);
//		mCar.setNName("");
//		mCar.setNSpeed(0.0f);
//		mCar.setNStatus(0);
//		mCar.setNXPosition(0.0f);
//		mCar.setNYPosition(0.0f);
//		mCar.setTimeDelay(0);
//		assertNotNull(mPool3.getOneCarInformation(0));
//		// assertEquals(mCar,mPool3.getOneCarInformation(0));
//	}
	
	public void testUpdateCarInformationsLogin(){
//		InforPoolClient mPool4 = new InforPoolClient();
//		PostToClient post = new PostToClient();
//		post.names[0] ="peter";
//		post.car[0]=0.0f;
//		post.status = 0;
//		mPool4.updateCarInformationsLogin(post);
//		mPool4.getOneCarInformation(0).getNName();
//		assertEquals(1, post.car.length);
		
		
		
		
		
	}
}
