package test.com.sa.xrace.client;

import junit.framework.TestCase;

import com.sa.xrace.client.R;
import com.sa.xrace.client.Rotate3dAnimation;

public class Rotate3dAnimationTest extends TestCase {

	Rotate3dAnimation R;
	
	protected void setUp() throws Exception
	{		
		R = new Rotate3dAnimation(1.0f,2.0f,3.0f,4.0f,5.0f,true,true);
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testRotate3dAnimation()
	{
		assertEquals("mFromDegrees is wrong!",1.0f,R.mFromDegrees);
		assertEquals("mToDegrees is wrong!",2.0f,R.mToDegrees);
		assertEquals("mCenterX is wrong!",3.0f,R.mCenterX);
		assertEquals("mCenterY is wrong!",4.0f,R.mCenterY);
		assertEquals("mDepthZ is wrong!",5.0f,R.mDepthZ);
		assertEquals("mReverse is wrong!",true,R.mReverse);
		assertEquals("misTurn is wrong!",true,R.misTurn);	
	}
	
}
