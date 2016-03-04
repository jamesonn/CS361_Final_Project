
/**
 * handles toggle and the triggering of different devices
 * @author Group 1
 */
public class Sensor {
	
	String deviceType;
	int sensorNum;
	boolean isToggledOn;
	
	/**
	 * 
	 * @param deviceType
	 * @param sensorNum
	 */
	public Sensor(String deviceType, int sensorNum){
		this.deviceType = deviceType;
		this.sensorNum = sensorNum;
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
}
