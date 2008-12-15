package test.com.sa.xrace.client.object;

import com.sa.xrace.client.loader.Scale;

import junit.framework.TestCase;

public class ScaleTest extends TestCase{
	
	public void testScale(){
		Scale scale= new Scale();
		
		scale.setX(0.1f);
		scale.setY(0.2f);
		scale.setZ(0.3f);
		
		assertEquals(0.1f, scale.getX());
		assertEquals(0.2f, scale.getY());
		assertEquals(0.3f, scale.getZ());
		
	}

}
