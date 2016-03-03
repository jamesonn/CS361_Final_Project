
public class Sensor {
	
	String deviceType;
	int sensorNum;
	boolean isToggledOn;
	
	public Sensor(String deviceType, int sensorNum){
		this.deviceType = deviceType;
		this.sensorNum = sensorNum;
	}
	
	public void triggerSensor(){
		if(isToggledOn){
			//do something
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
