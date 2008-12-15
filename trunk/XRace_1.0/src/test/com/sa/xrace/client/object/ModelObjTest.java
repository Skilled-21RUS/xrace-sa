package test.com.sa.xrace.client.object;

import com.sa.xrace.client.loader.ModelObj;
import com.sa.xrace.client.math.Point3f;

import junit.framework.TestCase;

public class ModelObjTest extends TestCase {

	public void testmethod(){
		
		ModelObj modelobj =new  ModelObj();
		modelobj.setFilename("abc");
		modelobj.setID("id");
		modelobj.setRadius(0.f);
		modelobj.setScale("0.1f,0.1f,0.1f");
		modelobj.setType("type");
		
		assertEquals("abc", modelobj.getFilename());
		assertEquals("id",modelobj.getID());
		assertEquals(0.f, modelobj.getRadius());
		assertEquals(0.1f, modelobj.getScale().x);
		assertEquals(0.1f, modelobj.getScale().y);
		assertEquals(0.1f, modelobj.getScale().z);
		assertEquals("type", modelobj.getType());
	}
	
}
