package test.com.sa.xrace.client.pool;

import junit.framework.TestCase;

import com.sa.xrace.client.pool.CarInforClient;

/**
 * @author Changpeng Pan
 * @version $Id: CarInforClientTest.java,v 1.1 2008-11-17 07:32:27 cpan Exp $
 */
public class CarInforClientTest extends TestCase {

	public CarInforClientTest(String name) {
		super(name);
	}

//	public void testSetMethod() {
//		CarInforClient mCar = new CarInforClient();
//
//		mCar.setNAcceleration(0);
//		mCar.setNAccidenceAcceleration(0);
//		mCar.setNAccidenceDirection(0);
//		mCar.setNAccidenceSpeed(0);
//		mCar.setNCarID(0);
//		mCar.setNCarType(0);
//		mCar.setNDirection(0.0f);
//		mCar.setNSpeed(0.0f);
//		mCar.setNStatus(0);
//		mCar.setNXPosition(0.0f);
//		mCar.setNYPosition(0.0f);
//		mCar.setTimeDelay(0);
//		mCar.setNName("Peter");
//
//		assertEquals(0, mCar.getNCarID());
//		assertEquals(0, mCar.getNCarType());
//
//		
//        mCar.setNDirection(0f);
//        mCar.setNStatus(0);
//
//		assertEquals(0, mCar.getNStatus());
//
//		assertEquals(0f, mCar.getNDirection());
//        
//		
//		assertEquals(0f, mCar.getNSpeed());
//		assertEquals(0f, mCar.getNAcceleration());
//
//		assertEquals(0f, mCar.getNXPosition());
//		assertEquals(0f, mCar.getNYPosition());
//
//		assertEquals(0f, mCar.getNAccidenceAcceleration());
//		assertEquals(0f, mCar.getNAccidenceSpeed());
//
//		assertEquals(0.0f, mCar.getNDirection());
//		assertEquals(0.0f, mCar.getNSpeed());
//		assertEquals(0.0f, mCar.getNAcceleration());
//		assertEquals(0.0f, mCar.getNXPosition());
//		assertEquals(0.0f, mCar.getNYPosition());
//		assertEquals(0.0f, mCar.getNAccidenceAcceleration());
//		assertEquals(0.0f, mCar.getNAccidenceSpeed());
//		assertEquals(0.0f, mCar.getNAccidenceDirection());
//		assertEquals(0, mCar.getTimeDelay());
//		assertEquals(0.0f, mCar.getNAccidenceDirection());
//		assertEquals(0, mCar.getTimeDelay());
//
//		assertEquals(0f, mCar.getNAccidenceDirection());
//		assertEquals(0, mCar.getTimeDelay());
//
//		mCar.setNName("Peter");
//
//		assertEquals("Peter", mCar.getNName());
//
//	}


	

	public void testGetMethod() {

		CarInforClient mCar2 = new CarInforClient();
		assertEquals(0, mCar2.getNCarID());
		assertEquals(0, mCar2.getNCarType());
		assertEquals(0, mCar2.getNStatus());
		assertEquals(0.0f, mCar2.getNDirection());
		assertEquals(0.0f, mCar2.getNSpeed());
		assertEquals(0.0f, mCar2.getNAcceleration());
		assertEquals(0.0f, mCar2.getNXPosition());
		assertEquals(0.0f, mCar2.getNYPosition());
		assertEquals(0.0f, mCar2.getNAccidenceAcceleration());
		assertEquals(0.0f, mCar2.getNAccidenceSpeed());
		assertEquals(0.0f, mCar2.getNAccidenceDirection());
		assertEquals(0, mCar2.getTimeDelay());
		assertEquals(0.0f, mCar2.getNAccidenceDirection());
		assertEquals(0, mCar2.getTimeDelay());

		assertEquals("", mCar2.getNName());

	}


}
