/**
 * handles toggle and the triggering of different devices
 * @author Group 1
 */
public class Sensor {
	
	private String deviceType;
	private int sensorNum;
	private boolean isToggledOn;
	
	/**
	 * 
	 * @param deviceType
	 * @param sensorNum
	 */

	public Sensor(String deviceType, int sensorNum, boolean sensorState){
		this.deviceType = deviceType;
		this.sensorNum = sensorNum;
        isToggledOn = sensorState;
	}
	
	/**
	 * checks if the toggle is on
	 * @return boolean
	 */
	public boolean canTriggerSensor(){
		return isToggledOn;		
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
	
	public String getDeviceType(){
		return this.deviceType;
	}
	
	public int getSensorNumber(){
		return this.sensorNum;
	}
}
