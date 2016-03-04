import java.util.ArrayList;


//Unfinished, working on it more tonight 3/1/2016

/**
 * Manages time, racer and lane information;
 * 
 * @author Group 1
 */
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

	/**
	 * saves and converts passed values into float systemTime
	 * @param hour
	 * @param minute
	 * @param second
	 */
	public void setTime(int hour, int minute, float second){
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		systemTime = (((hour*60)+minute)*60)+second;
	}
	
	/**
	 * creates a Racer and adds it to the first available lane
	 * @param racerNum
	 */
	public void addNum(int racerNum){
		if(lanesUsed > 4){
			lanesUsed = 0;
			Racers.add(new Racer(racerNum,lanesUsed)); //LanesUsed is just the laneNum
		}else{
			Racers.add(new Racer(racerNum,lanesUsed));
		}
		lanesUsed++;
	}
	
	/**
	 * starts a lane's racer based on channel passed
	 * @param channel
	 */
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
	
	/**
	 * stops a lane's racer based on channel passed
	 * @param channel
	 */
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
	
	/**
	 * determines if the lane corresponding with the 
	 * passed channel has started or not
	 * @param channel
	 * @return boolean
	 */
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
	/**
	 * calculates the time for...
	 */
	public void calculateTime(){}
}
