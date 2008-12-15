package test.com.sa.xrace.client.object;

import com.sa.xrace.client.loader.LocationObj;
import com.sa.xrace.client.loader.Point;

import junit.framework.*;
public class LocationObjTest extends TestCase {
	
	public void  testLocationObj(){
		
		LocationObj locationobj= new LocationObj();
		locationobj.setSize(11);
		assertEquals(11, locationobj.getSize());
        Point[] point= new Point[2];
        point[0] = new Point();
        point[0].setX(0.1f);
        point[0].setY(0.1f);
        point[0].setZ(0.1f);
        point[0].setAngle(0.1f);
        
        point[1] = new Point();
        point[1].setX(0.2f);
        point[1].setY(0.2f);
        point[1].setZ(0.2f);
        point[1].setAngle(0.2f);
		locationobj.setPoint(point);
		assertEquals(point,locationobj.getPointArray());
		
	}
	
}
