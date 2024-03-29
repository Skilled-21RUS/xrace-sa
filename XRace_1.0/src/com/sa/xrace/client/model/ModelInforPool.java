/* 
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sa.xrace.client.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.microedition.khronos.opengles.GL10;

import com.sa.xrace.client.GLThread_Room;
import com.sa.xrace.client.math.Point3f;
/**
 * @author sliao
 * @version $Id: ModelInforPool.java,v 1.11 2008-12-10 06:57:21 cpan Exp $
 */
/**
 *  This class is used to store and render the t3DModel objects.
 */
public class ModelInforPool
{
	public ModelInforPool(Point3f position)
	{
		mPosition = position;
		mScale = new Point3f(1.0f, 1.0f, 1.0f);
	}

	public void addModel(Model model)
	{
//		mModelVector.add(model);
		mModelVector.put(model.getID(), model);
	}
	
	public void addModel(int modelID, int type, t3DModel model, Point3f scale, float radius) 
	{
		Model mod = new Model(modelID, type, model, scale, radius);
//		mModelVector.add(mod);
		mModelVector.put(modelID, mod);
	}
	/**
	 *  This function would generate all the buffer for render according 
	 *  those data has been imported into the model objects.
	 */
	public void generate(GL10 gl)
	{	
//		Iterator<Model> ModelIterator = mModelVector.iterator();
		
		Collection<Model> collection = mModelVector.values();		
		Iterator<Model> modelIterator = collection.iterator();
		
		int size = mModelVector.size();        // for loading
		int interval = 180/size;
		
		while (modelIterator.hasNext()) 
		{
			int temp = GLThread_Room.progress;
			Model model = modelIterator.next();
			if(model.getType() != Model.COLLISION)
			{
				model.generate(gl);				
			}
			GLThread_Room.makeLoading(GLThread_Room.gl,GLThread_Room.progress+interval,3);
		}
	}

	/**
	 *  This function would render the current model
	 */
    public void draw(GL10 gl)
    {      
    	if (mCurrentModel != null)
    	{
    		gl.glMatrixMode(GL10.GL_MODELVIEW);
    		gl.glLoadIdentity();
    		//GLU.gluLookAt(gl, 0, 0, 0, 0, 0.1f, 0, 0, 1, 0);
    		
    		gl.glTranslatef(mPosition.x, mPosition.y, mPosition.z);	
    		gl.glScalef(mScale.x, mScale.y, mScale.z);
    		gl.glRotatef(mAngle, 0, 1, 0);
    		
    		if(mCurrentModel.getType() == Model.CAR)
    		{
    			mPresentationAngle +=1.2f;
    			if(mPresentationAngle >= 360f)
    			{
    				mPresentationAngle =0f;
    			}
    			gl.glRotatef(mPresentationAngle, 0, 1, 0);
    		}
    		
    		mCurrentModel.scale(gl);
    		mCurrentModel.draw(gl);	
    	}
    }
 
    public void drawByID(GL10 gl, int modelID)
    {
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		gl.glTranslatef(mPosition.x, mPosition.y, mPosition.z);	
		gl.glScalef(mScale.x, mScale.y, mScale.z);
		gl.glRotatef(mAngle, 0, 1, 0);
		
		mModelVector.get(modelID).scale(gl);
		
		//////////////////////////////////////////////
		if(modelID ==0 ||modelID == 1)///////////buhao
		{
			mPresentationAngle +=1.2f;
			if(mPresentationAngle >= 360f)
			{
				mPresentationAngle =0f;
			}
			gl.glRotatef(mPresentationAngle, 0, 1, 0);
		}
		mModelVector.get(modelID).draw(gl);
    	
    }

    public void setScale(float x, float y, float z)
    {
    	mScale.x = x;
    	mScale.y = y;
    	mScale.z = z;
    }
    public void setPosition(float x, float y, float z)
    {
    	mPosition.x =x;
    	mPosition.y =y;
    	mPosition.z =z;
    }
    public void setType(int type)
    {
    	this.mCurrentType = type;
//    	Iterator<Model> ModelIterator = mModelVector.iterator();
    	Collection<Model> collection = mModelVector.values();		
		Iterator<Model> modelIterator = collection.iterator();
		while (modelIterator.hasNext()) 
		{
			Model model = modelIterator.next();
			if (model.getType() == mCurrentType)
			{
				mCurrentModel = model;
				mAngle = 0.0f;
			}
		}
    }
    public void setTypeAndUpdate(int type , boolean previousOrNext)
    {
    	this.mCurrentType = type;
    	Collection<Model> collection = mModelVector.values();		
		Iterator<Model> modelIterator = collection.iterator();
		while (modelIterator.hasNext()) 
		{
			Model model = modelIterator.next();
			if (model.getType() == mCurrentType)
			{
				mCurrentModel = model;
				mAngle = 0.0f;
			}
		}
		updateCurrentModel(previousOrNext);
    }
    public int getType()
    {
    	return mCurrentType;
    }
    public Model getCurrentModel()
    {
    	return mCurrentModel;
    }
    
    /**
	 * @param currentModel the mCurrentModel to set
	 */
	public void setMCurrentModel(Model currentModel) 
	{
		mCurrentModel = currentModel;
	}
    
    public Model getModel(int modelID)
    {
//    	Iterator<Model> modelIterator = mModelVector.iterator();
//		while (modelIterator.hasNext()) 
//		{
//			Model model = modelIterator.next();
//			if (model.getID() == modelID)
//			{
//				return model;
//			}
//		}
    	return mModelVector.get(modelID);
    }
    
    public boolean updateCurrentModel(boolean previousOrNext)
    {
//    	Iterator<Model> modelIterator = mModelVector.iterator();
    	Collection<Model> collection = mModelVector.values();		
		Iterator<Model> modelIterator = collection.iterator();
		
    	Model previous = null;
		while (modelIterator.hasNext()) 
		{
			Model model = modelIterator.next();
			if (model.getType() == mCurrentType)
			{
				if (model == mCurrentModel)
				{
					if (previousOrNext == true)
					{
						if (previous == null)
						{
							break;
						}
						else
						{
							mCurrentModel = previous;
							mAngle = 0.0f;
							return true;
						}
					}
					else 
					{
				    	while (modelIterator.hasNext()) 
						{
				    		model = modelIterator.next();
							if (model.getType() == mCurrentType)
							{
								mCurrentModel = model;
								mAngle = 0.0f;
								return true;
							}
						}
					}
				}
				previous = model;
			}
		}
		return false;
    }
	public float getMAngle() {
		return mAngle;
	}

	public void setMAngle(float angle) {
		mAngle = angle;
	}
	
    private int mCurrentType = -1;
    private Model mCurrentModel = null;
    private float mAngle = 0.0f;
    private float mPresentationAngle =0.0f;
    private Point3f mScale;
    private Point3f mPosition;
	//private Vector<Model> mModelVector = new Vector<Model>();
	private HashMap<Integer, Model> mModelVector = new HashMap<Integer, Model>();
	
}


