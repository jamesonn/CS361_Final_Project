
/**
 * handles toggle and the triggering of different devices
 * @author Group 1
 */
public class Sensor {
	
	String deviceType;
	int sensorNum;
	boolean isToggledOn;
	boolean isStartSensor;
	
	/**
	 * 
	 * @param deviceType
	 * @param sensorNum
	 */
	public Sensor(String deviceType, int sensorNum){
		this.deviceType = deviceType;
		this.sensorNum = sensorNum;
		if(sensorNum == 1 || sensorNum == 3 || sensorNum == 5 || sensorNum == 7){
			isStartSensor = true;
		}else{
			isStartSensor = false;
		}
	}
	
	/**
	 * checks if the toggle is on
	 * @return boolean
	 */
	public boolean canTriggerSensor(){
		if(isToggledOn){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * toggles the boolean isToggledOn
	 */
	public void toggle(){
		if(isToggledOn){
			isToggledOn = false;
		}else{
			isToggledOn = true;
		}
	}
	
	public boolean isStartSensor(){
		return isStartSensor;
	}
}
