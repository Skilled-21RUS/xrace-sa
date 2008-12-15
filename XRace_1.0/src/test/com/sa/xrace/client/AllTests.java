package test.com.sa.xrace.client;

import test.com.sa.xrace.client.pool.CarInforClientTest;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Changpeng Pan
 * @version $Id: AllTests.java,v 1.1 2008-11-17 07:32:27 cpan Exp $
 */
public class AllTests {
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for test.com.sa.android");
		//$JUnit-BEGIN$
		
		suite.addTestSuite(CarInforClientTest.class);
//		suite.addTestSuite(point3fTest.class);
//		suite.addTestSuite(GLVertexTest.class);
//		suite.addTestSuite(matrix3fTest.class);
		
		//suite.addTestSuite(KubeLayerTest.class);
		
		
		// suite.addTestSuite(GLFaceTest.class);
		// suite.addTestSuite(GLColorTest.class);
		// suite.addTestSuite(M4Test.class);
		// suite.addTestSuite(Rotate3dAnimationTest.class);
		// suite.addTestSuite(MessAndsolveTest.class);
		
		//suite.addTestSuite(KubeTest.class);
		//$JUnit-END$
		
		return suite;
	}
}
