package com.sa.xrace.server.pool;

/**
 * @author Changpeng Pan
 * @version $Id: CarInforServer.java,v 1.1 2008-11-17 06:51:09 cpan Exp $
 */
public class CarInforServer {
	
	/**
	 * nName 				Car name
	 * nCarID 				Car ID
	 * nCarType 			Car type,include type,color
	 * nStatus				Current status of this car normal,accident,idle,login,collision,logout
	 * nDirection 			Current Direction
	 * nSpeed 				Current Speed
	 * nAcceleration 		Current Acceleration
	 * nXPosition 			Current X Position
	 * nYPosition 			Current Y Position
	 * timeDelay 			Time delay of this car in network transmit
	 */
	private String nName;//Car name
	private byte nCarID;//car ID
	private int nCarType;//Car type,include type,color 
	private byte nStatus;//Current status
	private float nDirection;//Current Direction
	private float nChangeDirection;//Current Direction Change...
	private float nSpeed;//Current Speed
	private float nAcceleration;//Current Acceleration
	private float nXPosition;//Current X Position
	private float nYPosition;//Current Y Position
	private short timeDelay;//time delay of this car in network transmit
	private int carListName;
	
	private float nAccidenceSpeed;
	private float nAccidenceAcceleration;
	private float nAccidenceDirection;
	private float nAccidenceChangeDirection;
	
	private int mModelID;	//for model ID

	public CarInforServer()
	{
		nCarID = 0;
		nCarType=0;//Car type,include type,color 
		nStatus=0;//Current Type
		nDirection=0;//Current Direction
		nChangeDirection=0;
		nSpeed=0;//Current Speed
		nAcceleration=0;//Current Acceleration
		nXPosition=200;//Current X Position
		nYPosition=200;//Current Y Position
		timeDelay=0;//time delay of this car in network transmit
		
		nAccidenceSpeed=0;
		nAccidenceAcceleration=0;
		nAccidenceDirection=0;
	}

	public String getNName() {
		return nName;
	}

	public void setNName(String name) {
		nName = name;
	}

	public byte getNCarID() {
		return nCarID;
	}

	public void setNCarID(byte carID) {
		nCarID = carID;
	}

	public int getNCarType() {
		return nCarType;
	}

	public void setNCarType(int carType) {
		nCarType = carType;
	}

	public byte getNStatus() {
		return nStatus;
	}

	public void setNStatus(byte status) {
		nStatus = status;
	}

	public float getNDirection() {
		return nDirection;
	}

	public void setNDirection(float direction) {
		nDirection = direction;
	}

	public float getNChangeDirection() {
		return nChangeDirection;
	}

	public void setNChangeDirection(float changeDirection) {
		nChangeDirection = changeDirection;
	}

	public float getNSpeed() {
		return nSpeed;
	}

	public void setNSpeed(float speed) {
		nSpeed = speed;
	}

	public float getNAcceleration() {
		return nAcceleration;
	}

	public void setNAcceleration(float acceleration) {
		nAcceleration = acceleration;
	}

	public float getNXPosition() {
		return nXPosition;
	}

	public void setNXPosition(float position) {
		nXPosition = position;
	}

	public float getNYPosition() {
		return nYPosition;
	}

	public void setNYPosition(float position) {
		nYPosition = position;
	}

	public short getTimeDelay() {
		return timeDelay;
	}

	public void setTimeDelay(short timeDelay) {
		this.timeDelay = timeDelay;
	}

	public float getNAccidenceSpeed() {
		return nAccidenceSpeed;
	}

	public void setNAccidenceSpeed(float accidenceSpeed) {
		nAccidenceSpeed = accidenceSpeed;
	}

	public float getNAccidenceAcceleration() {
		return nAccidenceAcceleration;
	}

	public void setNAccidenceAcceleration(float accidenceAcceleration) {
		nAccidenceAcceleration = accidenceAcceleration;
	}

	public float getNAccidenceDirection() {
		return nAccidenceDirection;
	}

	public void setNAccidenceDirection(float accidenceDirection) {
		nAccidenceDirection = accidenceDirection;
	}

	public float getNAccidenceChangeDirection() {
		return nAccidenceChangeDirection;
	}

	public void setNAccidenceChangeDirection(float accidenceChangeDirection) {
		nAccidenceChangeDirection = accidenceChangeDirection;
	}

	/**
	 * @return the mModelID
	 */
	public int getMModelID() {
		return mModelID;
	}

	/**
	 * @param modelID the mModelID to set
	 */
	public void setMModelID(int modelID) {
		mModelID = modelID;
	}

	public int getCarListName() {
		return carListName;
	}

	public void setCarListName(int carListName) {
		this.carListName = carListName;
	}

}
