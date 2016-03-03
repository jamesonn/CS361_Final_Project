import java.util.ArrayList;


//Unfinished, working on it more tonight 3/1/2016
public class Timer {
	ArrayList<Racer> Racers = new ArrayList<Racer>();
	Lane lane;
	Lane[] lanes = new Lane[4];
	int lanesUsed;
	int hour;
	int minute;
	float second;
	float systemTime;
	boolean laneStarted = false;

	public void setTime(int hour, int minute, float second){
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		systemTime = (((hour*60)+minute)*60)+second;
	}
	
	public void addNum(int racerNum){
		if(lanesUsed > 4){
			lanesUsed = 0;
			Racers.add(new Racer(racerNum,lanesUsed)); //LanesUsed is just the laneNum
		}else{
			Racers.add(new Racer(racerNum,lanesUsed));
		}
		lanesUsed++;
	}
	
	public void start(int channel){
		int workingLane = 0;
		for(int i = 0; i < Racers.size(); i++){
			Racer racer = Racers.get(i);
			if(channel == 1 || channel == 2){
				workingLane = 0;
			}
			if(channel == 3 || channel == 4){
				workingLane = 1;
			}
			if(channel == 5 || channel == 6){
				workingLane = 2;
			}
			if(channel == 7 || channel == 28){
				workingLane = 3;
			}
			if(racer.laneNum == workingLane){
				racer.startTime = systemTime;
			}
		}		
	}
	
	public void stop(int channel){
		int workingLane = 0;
		for(int i = 0; i < Racers.size(); i++){
			Racer racer = Racers.get(i);
			if(channel == 1 || channel == 2){
				workingLane = 0;
			}
			if(channel == 3 || channel == 4){
				workingLane = 1;
			}
			if(channel == 5 || channel == 6){
				workingLane = 2;
			}
			if(channel == 7 || channel == 28){
				workingLane = 3;
			}
			if(racer.laneNum == workingLane){
				racer.stopTime = systemTime;
			}
		}
	}
	
	public boolean hasLaneStarted(int channel){
		if(channel == 1 || channel == 2){
			if(lanes[0].hasStarted){
				return true;
			}
		}
		if(channel == 3 || channel == 4){
			if(lanes[1].hasStarted){
				return true;
			}
		}
		if(channel == 5 || channel == 6){
			if(lanes[2].hasStarted){
				return true;
			}
		}
		if(channel == 7 || channel == 8){
			if(lanes[3].hasStarted){
				return true;
			}
		}
		return false;
	}
	public void calculateTime(){}
}
