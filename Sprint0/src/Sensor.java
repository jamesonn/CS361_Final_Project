
public class Sensor {
	
	String deviceType;
	int sensorNum;
	boolean isToggledOn;
	Racer racer;
	
	public Sensor(String deviceType, int sensorNum){
		this.deviceType = deviceType;
		this.sensorNum = sensorNum;
	}
	
	public void setRacer(Racer racer){
		this.racer = racer;
	}
	
	public void triggerSensor(){
		if(isToggledOn){
			if(racer.hasStarted){
				
			}else{
				
			}
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
