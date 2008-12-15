package com.sa.xrace.client.pool;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.sa.xrace.client.GLThread_Room;
import com.sa.xrace.client.GameActivity;
import com.sa.xrace.client.R;


public class RoomPicPool {
	

	public static ByteBuffer down_PicB;
	public static ByteBuffer mapchoice_PicB;
	public static ByteBuffer mapview1_PicB;
	public static ByteBuffer mapview2_PicB;
	public static ByteBuffer carchoice_PicB;	
	public static ByteBuffer carview1_PicB;
	public static ByteBuffer car_PicB0;
	public static ByteBuffer car_PicB1;
	public static ByteBuffer car_PicB2;
	public static ByteBuffer car_PicB3;
	public static ByteBuffer loading;
	
	public  Bitmap carB_img,carMyB_img;
	public 	Bitmap speedometer,speedTriangle;
	public static Bitmap load0,load1,load2,load3;
	public  Bitmap minimap;
	
	public GameActivity activity;
	public IntBuffer tempIB;
	public Bitmap carpoint;
	
	public RoomPicPool(GameActivity activity){
		this.activity = activity;
		load0 = getBitmap(R.drawable.load0);
	}

	public Bitmap getBitmap(int resID){
		Bitmap tem = BitmapFactory.decodeResource(activity.getResources(),resID);
		return tem;
	}
	
	public void generateEveryThing()
	{
		down_PicB = getImageReadyfor(R.drawable.car_down);
		mapchoice_PicB= getImageReadyfor(R.drawable.mapchoice_pic);	
		mapview1_PicB = getImageReadyfor(R.drawable.mapview1);
		load1 = getBitmap(R.drawable.load1);
		GLThread_Room.makeLoading(GLThread_Room.gl,82,0);
		
		mapview2_PicB = getImageReadyfor(R.drawable.mapview2);
		carchoice_PicB= getImageReadyfor(R.drawable.carchoice);
		load2 = getBitmap(R.drawable.load2);
		speedTriangle = getBitmap(R.drawable.triangle);
		GLThread_Room.makeLoading(GLThread_Room.gl,102,1);
		
		carB_img = getBitmap(R.drawable.upleft_pic);
		carview1_PicB= getImageReadyfor(R.drawable.carchoice);
		carMyB_img = getBitmap(R.drawable.mysite_pic);
		speedometer = getBitmap(R.drawable.speedometer);
		carpoint = getBitmap(R.drawable.carpointpic);
		minimap =  getBitmap(R.drawable.minimap);
		load3 = getBitmap(R.drawable.load3);
		GLThread_Room.makeLoading(GLThread_Room.gl,162,1);
	}
	
	public ByteBuffer getImageReadyfor(int resname) {
		ByteBuffer tempBuffer;
		Bitmap mBitmap = BitmapFactory.decodeResource(activity.getResources(), resname);
		int pic_width = mBitmap.getWidth();
		int pic_height = mBitmap.getHeight();
		// Log.e("pic_width",""+pic_width+" "+pic_height);
		tempBuffer = ByteBuffer.allocateDirect(pic_width * pic_height * 4);
		tempBuffer.order(ByteOrder.nativeOrder());
		tempIB = tempBuffer.asIntBuffer();

		for (int y = 0; y < pic_width; y++) {
			for (int x = 0; x < pic_height; x++) {
				tempIB.put(mBitmap.getPixel(x, y));
			}
		}

		for (int i = 0; i < pic_width * pic_height * 4; i += 4) {
			byte temp = tempBuffer.get(i);
			tempBuffer.put(i, tempBuffer.get(i + 2));
			tempBuffer.put(i + 2, temp);

		}
		tempBuffer.position(0);
		return tempBuffer;
	}

}
