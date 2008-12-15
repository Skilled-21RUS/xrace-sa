package com.sa.xrace.client.objects;
/**
 * @author yzhong
 * @version $Id: TestSceneParserActivity.java,v 1.1 2008-11-17 07:32:26 cpan Exp $
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.sa.xrace.client.*;
//import com.sa.android.R;

public class TestSceneParserActivity extends Activity {
	private static SenceObj sence;
	InputStream is;
	Document document;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);		
		
		// here show you how to fetch data from the object Sence
//		Button sBtn = (Button)findViewById(R.id.btn);
//		Log.e("test","begin");
//		sBtn.setOnClickListener(
		new View.OnClickListener()
		{
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					is = getAssets().open("scene.xml");	
					document = new SAXReader().read(new InputStreamReader(is));		
					SenceParser sp = new SenceParser(document);
					sence = sp.getScene();	
					Log.e("test",sence.toString());
					Log.e("test",sence.getName());	
					//Log.e("test",sence.getLModelList().toString());
					 
					ArrayList al = sence.getLModelList();
					Log.e("test al",al.size()+al.toString());
					
					ModelObj mb = null;
					LocationObj location = null;
					Point point = null;
					//Iterator all Model
					for(int i = 0;i<al.size();i++){
						mb = (ModelObj)al.get(i);
						Log.e("test mb",mb.toString());
						Log.e("test mb",mb.getFilename());
						Log.e("test mb",mb.getID());
						Log.e("test mb",mb.getType());
						Log.e("test mb",mb.getRadius()+"");
						Log.e("test mb",mb.getScale()+"");
						Log.e("test mb",mb.getLocation().toString());
						
						//get the location from Model
						location = mb.getLocation();
						Log.e("test location size",location.getSize()+"");
						//get all Points from location
						Point[] ps = location.getPointArray();
						for (int k = 0 ;k<ps.length;k++){
							point = ps[i];
							//Log the data from point :x ,y , z,angle
							Log.e("test poing values",point.getX()+"");
							Log.e("test poing values",point.getY()+"");
							Log.e("test poing values",point.getZ()+"");
							Log.e("test poing values",point.getAngle()+"");
						}
					}
				}catch(IOException e){
					e.printStackTrace();
				}catch (DocumentException e) {
					e.printStackTrace();
				}
			}
		
		};
//		);
	
	}
}
