package com.sa.xrace.server.pool;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Vector;

import com.sa.xrace.server.data.GameMapData;

/**
 * @author Changpeng Pan
 * @version $Id: InforPoolServer.java,v 1.4 2008-12-05 07:29:02 twei Exp $
 */
public class InforPoolServer {

	private static final String TAG = "-- InforPoolServer --";
	public static final byte LOGIN = 101;
	public static final byte LOGOUT = 100;
	public static final byte NORMAL = 10;
	public static final byte ACCIDENT = 20;
	public static final byte IDLE = 110;
	public static final byte DROPOUT = 30;
	public static final byte START = 111;
	public static final byte CARTYPE = 14;
	public static final int MAXCARN = 4;
	private static final int NEWCAR_FLAG = 10;
	private static final int LOGIN_FLAG = 2;
	private static final int LOGOUT_FLAG = 3;
	private static final int IDLE_FLAG = 1;
	private static final int MAXIMUN_FLAG = 50;
	private static int nCarNumber = 0;
	public static CarInforServer[] nCar;
	public static int[] listCar;
	public static GameMapData nGameMapData;
	private Vector<DataOutputStream> outSVector = new Vector<DataOutputStream>(0);

	private static Vector<Socket> SocketVector = new Vector<Socket>(0);
	private int tempLName;
	private static int outer;
	private static int outerCarListName;
	private static boolean isAccident = false;

	private String SInputName;
	public static final byte LOGINFAILURE =102;

	/**
	 * Initiate the car capacity in information pool to 6
	 */
	public InforPoolServer() {
		nCar = new CarInforServer[6];
		listCar = new int[MAXCARN];
		for (int i = 0; i < 6; i++) {
			nCar[i] = new CarInforServer();
		}
		for (int i = 0; i < MAXCARN; i++) {
			listCar[i] = i * 100;
		}
//		setNCarNumber(4);

	}

	/**
	 * Get a handle of the vector, which stores those output stream of each
	 * socket
	 */
	public Vector<DataOutputStream> getOutSVector() {
		return outSVector;
	}

	/**
	 * Returns an nCar at indicated position Gets the entire information of a
	 * car
	 */
	public CarInforServer getOneCarInformation(int car) {
		return nCar[car];
	}

	/**
	 * Returns the number of how many car online now
	 */
	public int getNCarNumber() {
		return nCarNumber;
	}

	/**
	 * Sets the number of how many car online now
	 */
	public void setNCarNumber(int carNumber) {
		nCarNumber = carNumber;
	}

	/**
	 * Gets the index of the one who dropped out or logged out
	 */
	public static int getOuter() {
		return outer;
	}

	/**
	 * Sets the index of the one who dropped out or logged out
	 */
	public static void setOuter(int outer) {
		InforPoolServer.outer = outer;
	}

	/**
	 * Gets a handle of the vector, which stores those socket online
	 */
	public Vector<Socket> getSocketVector() {
		return SocketVector;
	}

	/**
	 * Gets the flag, which indicates whether there is accident this time
	 */
	public static boolean getIsAccident() {
		return isAccident;
	}

	/**
	 * Sets the accident flag
	 */
	public static void setAccident(boolean isAccident) {
		InforPoolServer.isAccident = isAccident;
	}

	/**
	 * Deals with an userLogin requirement Sets the basic information of that
	 * car Increases the nCarNumber
	 */

	public void updateUserLogin(String name, Socket inputSocket,
			DataOutputStream ouputStream) {
		SInputName = name;
		while (SInputName.length() < 10) {
			SInputName += "-";
		}
		nCar[nCarNumber].setNName(SInputName);
		nCar[nCarNumber].setNStatus(IDLE);
		nCar[nCarNumber].setNCarID((byte) nCarNumber);
		SocketVector.add(inputSocket);
		outSVector.add(ouputStream);

		for(int i=0;i<MAXCARN;i++)
		{
			if(listCar[i]%10 == 0)//means there is the room for this new car
			{
				listCar[i]=i*100 +NEWCAR_FLAG+LOGIN_FLAG;
				nCar[nCarNumber].setCarListName(i*100 +NEWCAR_FLAG+LOGIN_FLAG);

				System.out.println("====================================");
				System.out.println("nCarNumber "+nCarNumber);
				System.out.println("carListName "+nCar[nCarNumber].getCarListName());
				System.out.println("====================================");
				
				break;
			}
		}

		nCarNumber += 1;
	}

	public int getListCar(int index) {
		return listCar[index];
	}

	public void loginReplyProcess() {
		for(int i=0;i<MAXCARN;i++)
		{
			if(listCar[i]%100 >= NEWCAR_FLAG)
			{
				listCar[i]=i*100 + IDLE_FLAG;
			}
		}
		for(int i=0;i<getNCarNumber();i++)
		{
			tempLName = getOneCarInformation(i).getCarListName();
			if(tempLName%100 >= NEWCAR_FLAG)
			{
				getOneCarInformation(i).setCarListName((tempLName/100)*100 + IDLE_FLAG);//to n01
			}
		}
	}

	private void removeCarName(int carListName) {
		for(int i=0;i<MAXCARN;i++)
		{
			if(listCar[i]/100 == carListName/100) //player with this name, out!
			{
				listCar[i] = i*100;
			}
		}
	}

	/**
	 * Gets a handle of the vector, which stores those socket online
	 */
	public void updateUserLogout(byte id, Socket inputSocket,
			DataOutputStream ouputStream) {
		int position = (byte) id;

		setOuterCarListName(nCar[position].getCarListName());
		removeCarName(nCar[position].getCarListName());

		System.out.println("position" + position);
		System.out.println("CarListName" + nCar[position].getCarListName());

		moveTheRestCarForward(position);
		SocketVector.remove(inputSocket);
		outSVector.remove(ouputStream);
		setOuter(position);

		nCarNumber -= 1;

	}

	/**
	 * Deals with an userDropOut event deletes his information and move those
	 * carinformations behind to forward positions decreases nCarNumber
	 */
	public void updateUserDropedOut(Socket inputSocket,
			DataOutputStream ouputStream) {
		if(nCarNumber == 0)
		{
			//do nothing
		}else
		{
		int position = SocketVector.indexOf(inputSocket);

		setOuterCarListName(nCar[position].getCarListName());
		removeCarName(nCar[position].getCarListName());

		System.out.println("position" + position);
		System.out.println("CarListName" + nCar[position].getCarListName());

		moveTheRestCarForward(position);
		SocketVector.remove(inputSocket);
		outSVector.remove(ouputStream);
		setOuter(position);

		nCarNumber -= 1;
		}
	}

	/**
	 * Reads an input index. Move the carinformations behind that index forward.
	 */
	private void moveTheRestCarForward(int position) {
		for (int i = position; i < nCarNumber; i++) {
			copyCarToForwadPostion(i + 1);
		}
	}

	/**
	 * Copies the car information to the forward position
	 */
	private void copyCarToForwadPostion(int nowPosition) {
		try {
			nCar[nowPosition - 1].setNCarID((byte) (nCar[nowPosition].getNCarID() - 1));
			nCar[nowPosition - 1].setNAcceleration(nCar[nowPosition].getNAcceleration());
			nCar[nowPosition - 1].setNCarType(nCar[nowPosition].getNCarType());
			nCar[nowPosition - 1].setNDirection(nCar[nowPosition].getNDirection());
			nCar[nowPosition - 1].setNName(nCar[nowPosition].getNName());
			nCar[nowPosition - 1].setNSpeed(nCar[nowPosition].getNSpeed());
			nCar[nowPosition - 1].setNXPosition(nCar[nowPosition].getNXPosition());
			nCar[nowPosition - 1].setTimeDelay(nCar[nowPosition].getTimeDelay());
			nCar[nowPosition - 1].setNYPosition(nCar[nowPosition].getNYPosition());
			nCar[nowPosition - 1].setNChangeDirection(nCar[nowPosition].getNChangeDirection());
			nCar[nowPosition - 1].setCarListName(nCar[nowPosition].getCarListName());
			nCar[nowPosition - 1].setMModelID(nCar[nowPosition].getMModelID());
		} catch (Exception e) {
			System.out.println(TAG + "copyCarToForwadPostion()");
		}
	}

	/**
	 * Returns the String[] of the user names who have logined
	 */
	public String getLoginedNames() {
		SInputName = new String();
		for (int i = 0; i < nCarNumber; i++) {
			SInputName += getOneCarInformation(i).getNName();
		}
		return SInputName;
	}

	/**
	 * Returns the float[] of the user IDs who have logined
	 */
	public byte[] getAllCarIDs() {
		byte[] tempBytes = new byte[nCarNumber];
		for (int i = 0; i < nCarNumber; i++) {
			tempBytes[i] = getOneCarInformation(i).getNCarID();
		}
		return tempBytes;
	}

	/**
	 * Gets an float[] contains the information to describe each car in Logout
	 * situation for generating the PostToClient
	 */
	public byte getLogoutDropoutCarID() {
		return (byte) getOuter();
	}

	/**
	 * Updates the information which describes each car in start situation Set
	 * all cars to NORMAL
	 */
	public void updateUserCarStart() {
		for (int i = 0; i < getNCarNumber(); i++) {
			getOneCarInformation(i).setNStatus(NORMAL);
		}
	}
	
	public void updteUserCarType(byte id,byte model){
		int index = (int)id;
		nCar[index].setMModelID((int)model);
	}

	/**
	 * Updates the information which describes each car in accident situation
	 */
	public void updateUserCarNoamal(byte id, float speed, float direction,
			float acceleration, float dirChange, float xposition,
			float yposition, short timedelay) {
		// get the index of current car which send the post to the server.
		// and the car id save on the array of the car
		int index = (int) id;
		nCar[index].setNSpeed(speed);
		nCar[index].setNDirection(direction);
		nCar[index].setNAcceleration(acceleration);
		nCar[index].setNChangeDirection(dirChange);
		nCar[index].setNXPosition(xposition);
		nCar[index].setNYPosition(yposition);
		nCar[index].setTimeDelay(timedelay);
	}

	/**
	 * Updates the information which describes each car in accident situation
	 */
	public void updateUserCarAccident(byte id, float speed, float direction,
			float acceleration, float dirChange, float xposition,
			float yposition, short timedelay) {
		int index = (int) id;
		
		nCar[index].setNAccidenceSpeed(speed);
		nCar[index].setNAccidenceDirection(direction);
		nCar[index].setNAccidenceAcceleration(acceleration);
		
		nCar[index].setNChangeDirection(dirChange);
		nCar[index].setNXPosition(xposition);
		nCar[index].setNYPosition(yposition);
		nCar[index].setTimeDelay(timedelay);
		
		
	}

	/**
	 * Updates the information which describes each car in accident situation
	 * Time synchronization
	 */
	public void updateUserCarIdle(byte id, short timeDelay) {
		int index = (int) id;
		nCar[index].setNStatus(InforPoolServer.IDLE);
		nCar[index].setTimeDelay(timeDelay);
	}

	/**
	 * Returns the Delayed time of a car
	 */
	public int getOneCarDelayTime(int index) {
		return nCar[index].getTimeDelay();
	}

	/**
	 * Fix Long time to int
	 */
	public static int timeFixing(Long input) {

		return (int) (input % 1000000000 % 10000);
	}

	public static short IntToShort(int input) {
		return (short) input;
	}

	public static int getOuterCarListName() {
		return outerCarListName;
	}

	public static void setOuterCarListName(int outerCarListName) {
		InforPoolServer.outerCarListName = outerCarListName;
	}

	public static void LogOutListCar() {
		for (int i = 0; i < MAXCARN; i++) {
			System.out.println("listCar" + i + " " + listCar[i]);
		}
	}
	public void LogAllCarInfor() {
		if (getNCarNumber() == 0) {
			System.out.println("one car infor=====================================");
			System.out.println("car NAcceleration "+ nCar[0].getNAcceleration());
			System.out.println("car NCarID " + nCar[0].getNCarID());
			System.out.println("car NCarType " + nCar[0].getNCarType());
			System.out.println("car NDirection " + nCar[0].getNDirection());
			System.out.println("car NName " + nCar[0].getNName());
			System.out.println("car NSpeed " + nCar[0].getNSpeed());
			System.out.println("car NStatus " + nCar[0].getNStatus());
			System.out.println("car NXPosition " + nCar[0].getNXPosition());
			System.out.println("car NYPosition " + nCar[0].getNYPosition());
			System.out.println("car TimeDelay " + nCar[0].getTimeDelay());
			System.out.println("car CarListName " + nCar[0].getCarListName());
			System.out
					.println("one car infor=====================================");
		} else {
			for (int i = 0; i < getNCarNumber(); i++) {
				System.out.println("multiple car infor=====================================");
				System.out.println("car NAcceleration "+ nCar[i].getNAcceleration());
				System.out.println("car NCarID " + nCar[i].getNCarID());
				System.out.println("car NCarType " + nCar[i].getNCarType());
				System.out.println("car NDirection " + nCar[i].getNDirection());
				System.out.println("car NName " + nCar[i].getNName());
				System.out.println("car NSpeed " + nCar[i].getNSpeed());
				System.out.println("car NStatus " + nCar[i].getNStatus());
				System.out.println("car NXPosition " + nCar[i].getNXPosition());
				System.out.println("car NYPosition " + nCar[i].getNYPosition());
				System.out.println("car TimeDelay " + nCar[i].getTimeDelay());
				System.out.println("car CarListName "+ nCar[i].getCarListName());
				System.out.println("multiple car infor=====================================");
			}
		}
	}
	
}
