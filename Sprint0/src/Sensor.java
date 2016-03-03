
public class Sensor {
	
	String deviceType;
	int sensorNum;
	boolean isToggledOn;
	
	public Sensor(String deviceType, int sensorNum){
		this.deviceType = deviceType;
		this.sensorNum = sensorNum;
	}
	
	public boolean canTriggerSensor(){
		if(isToggledOn){
			return true;
		}else{
			return false;
		}
	}
	
	public void toggle(){
		if(isToggledOn){
			isToggledOn = false;
		}else{
			isToggledOn = true;
		}
	}
}
