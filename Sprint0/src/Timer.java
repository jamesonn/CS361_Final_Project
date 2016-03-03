import java.util.ArrayList;


//Unfinished, working on it more tonight 3/1/2016
public class Timer {
	ArrayList<Racer> Racers = new ArrayList<Racer>();
	int lane = 0;
	int hour;
	int minute;
	float second;
	float systemTime;

	public void setTime(int hour, int minute, float second){
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		systemTime = (((hour*60)+minute)*60)+second;
	}
	
	public void addNum(int racerNum){
		Racers.add(new Racer(racerNum, lane));
		if(lane > 4){
			lane = 0;
		}
		lane++;
	}
	
	public void start(int laneNum){
		for(int i = 0; i < Racers.size(); i++){
			Racer racer = Racers.get(i);
			if(racer.laneNum == laneNum){
				racer.startTime = systemTime;
				racer.hasStarted = true;
			}
		}		
	}
	
	public void stop(int laneNum){
		for(int i = 0; i < Racers.size(); i++){
			Racer racer = Racers.get(i);
			if(racer.laneNum == laneNum){
				racer.stopTime = systemTime;
				racer.hasStarted = false;
			}
		}
	}	
	public void calculateTime(){}
}
