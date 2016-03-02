
public class Sensor {
	
	String deviceType;
	int sensorNum;
	boolean isToggledOn;
	
	public Sensor(String deviceType, int sensorNum){
		this.deviceType = deviceType;
		this.sensorNum = sensorNum;
	}
	
	public void triggerSensor(){
		
	}
	
	public void toggleOn(){
		isToggledOn = true;
	}
	
	public void toggleOff(){
		isToggledOn = false;
	}
}
