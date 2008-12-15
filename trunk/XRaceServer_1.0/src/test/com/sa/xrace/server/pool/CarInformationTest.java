package test.com.sa.xrace.server.pool;

import junit.framework.TestCase;

import com.sa.xrace.server.pool.CarInforServer;
/**
 * @author jianhuang
 * @version $Id: CarInformationTest.java,v 1.1 2008-11-17 06:51:09 cpan Exp $
 */
public class CarInformationTest extends TestCase {

	private static final long serialVersionUID = 1L;
	public CarInformationTest(String nName){
		super(nName);
	}
	public void testGetmethod(){
		CarInforServer cif= new CarInforServer();
		cif.setNName("abc");
		assertEquals("abc", cif.getNName());
		System.out.println(cif.getNName());
//		cif.setNStatus(100);
		assertEquals(100, cif.getNStatus());
		System.out.println(cif.getNStatus());
		cif.setNXPosition(10.2f);
		assertEquals(10.2f, cif.getNXPosition());
		System.out.println(cif.getNXPosition());
	}


}
