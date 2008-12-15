package com.sa.xrace.client.loader;
import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.tree.DefaultElement;

public class SenceParser {

	private SenceObj sence;
	private Document doc ;
	public SenceParser(Document doc) {
		if (doc != null) {
			this.doc = doc;
			sence = getSenceObj();
		}
	}
	
	public SenceObj getScene()
	{
		return this.sence;
	}

	// get Sence Object
	public SenceObj getSenceObj() {
		SenceObj senceTemp = new SenceObj();
		senceTemp.setName(getSenceName());
		senceTemp.setLModelList(getModelList());
		return senceTemp;
	}

	private ArrayList<ModelObj> getModelList() {
		ArrayList<ModelObj> modelListTemp = new ArrayList<ModelObj>();
		Element root =(Element) doc.getRootElement();
		ModelObj mObjTemp = null;
		Iterator it = root.elementIterator("Model");
		while(it.hasNext()){
			Element eModel = (Element)it.next();
			mObjTemp = new ModelObj();	
			//set all attributes
			mObjTemp.setFilename(eModel.attribute(0).getValue());
			mObjTemp.setID(eModel.attribute(1).getValue());
			mObjTemp.setType(eModel.attribute(2).getValue());
			mObjTemp.setScale(eModel.attribute(3).getValue());
			mObjTemp.setRadius((Float.parseFloat(eModel.attribute(4).getValue())));
			//setLocation
			if(eModel.element("Location") != null){
				mObjTemp.setLocation(getLocation(eModel.element("Location")));		
			}
			modelListTemp.add(mObjTemp);
		}
		return modelListTemp;
	}
	
	private LocationObj getLocation(Element eLocationTemp) {		
		LocationObj locationTemp = new LocationObj();
		int locationSize = Integer.parseInt(eLocationTemp.attribute(0).getValue());
		locationTemp.setSize(locationSize);
		
		if(locationSize > 0){
			Point[] allPointTemp = new Point[locationSize];
			Iterator it = eLocationTemp.elementIterator("point");				
			Point pTemp = null;
			int i = 0;
			while (it.hasNext()){
				pTemp = new Point();
				Element ePoint = (Element)it.next();
				pTemp.setX(Float.parseFloat(ePoint.attribute(0).getValue()));
				pTemp.setY(Float.parseFloat(ePoint.attribute(1).getValue()));
				pTemp.setZ(Float.parseFloat(ePoint.attribute(2).getValue()));
				pTemp.setAngle(Float.parseFloat(ePoint.attribute(3).getValue()));
				allPointTemp[i] = pTemp;
				i++;
			}
			locationTemp.setPoint(allPointTemp);
			return locationTemp;
		}else{
			return locationTemp;
		}	
	}

	private String getSenceName() {
		Node nSence = this.doc.selectSingleNode("//Scene");
		DefaultElement eSence = (DefaultElement) nSence;
		return eSence.attribute(0).getValue();
	}
}
