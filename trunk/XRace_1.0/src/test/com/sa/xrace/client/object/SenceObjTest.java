package test.com.sa.xrace.client.object;

import java.util.ArrayList;

import com.sa.xrace.client.loader.ModelObj;
import com.sa.xrace.client.loader.SenceObj;

import junit.framework.TestCase;

public class SenceObjTest extends TestCase {

	private ArrayList<ModelObj> lModelList;
	protected void setUp() throws Exception
	{		
		super.setUp();
	}
	
	public void testSenceObj(){
		
		SenceObj s = new SenceObj();
		s.setLModelList(lModelList);
		s.setName("myname");
		assertEquals(lModelList, s.getLModelList());
		assertEquals("myname", s.getName());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	
}
