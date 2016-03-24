
/**
 * handles toggle and the triggering of different devices
 * @author Group 1
 */
public class Sensor {
	
	private String deviceType;
	private int sensorNum;
	private boolean isToggledOn;
	private boolean isStartSensor;
	
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
		return isToggledOn;		
	}
	
	/**
	 * toggles the boolean isToggledOn
	 */
	public void toggle(){
			isToggledOn = !isToggledOn;		
	}
	
	public boolean isStartSensor(){
		return isStartSensor;
	}
	
	public String getDeviceType(){
		return this.deviceType;
	}
	
	public int getSensorNumber(){
		return this.sensorNum;
	}
}
