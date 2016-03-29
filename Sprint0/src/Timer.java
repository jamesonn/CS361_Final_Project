import java.util.ArrayList;


//Unfinished, working on it more tonight 3/1/2016

/**
 * Manages time, racer and lane information;
 * 
 * @author Group 1
 */
public class Timer {
	//ArrayList<Racer> Racers = new ArrayList<Racer>();
	//Lane lane;
	Lane[] lanes = new Lane[4];
	int lanesUsed;
	int hour;
	int minute;
	float second;
	float systemTime;
	//boolean laneStarted = false;
	
	public Timer(){
		lanes[0] = new Lane();
		lanes[1] = new Lane();
		lanes[2] = new Lane();
		lanes[3] = new Lane();
	}

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
		systemTime = (hour*60)+(minute*60)+second;
	}
	
	/**
	 * creates a Racer and adds it to the first available lane
	 * @param racerNum
	 */
	public void addNum(int racerNum){
		if(lanesUsed >= 4){
			lanesUsed = 0;
		}
		lanes[lanesUsed].addRacer(new Racer(racerNum,lanesUsed));
		lanesUsed++;
	}
	
	/**
	 * starts a lane's racer based on channel passed
	 * @param channel
	 */
	public void start(int channel){
		if(!lanes[channel-1].isReadyEmpty())
			lanes[channel-1].start(systemTime);	
	}
	
	/**
	 * stops a lane's racer based on channel passed
	 * @param channel
	 */
	public String stop(int channel){
		Racer runner;
		System.out.println(channel);
		if(!lanes[channel-2].isActiveEmpty()){
			return lanes[channel-2].stop(systemTime);
		}
		else{
			return "hasn't started";
		}
	}
	
//	/**
//	 * determines if the lane corresponding with the 
//	 * passed channel has started or not
//	 * @param channel
//	 * @return boolean
//	 */
//	public boolean hasLaneStarted(int channel){
//		if(channel == 1 || channel == 2){
//			if(lanes[0].hasStarted){
//				return true;
//			}
//		}
//		if(channel == 3 || channel == 4){
//			if(lanes[1].hasStarted){
//				return true;
//			}
//		}
//		if(channel == 5 || channel == 6){
//			if(lanes[2].hasStarted){
//				return true;
//			}
//		}
//		if(channel == 7 || channel == 8){
//			if(lanes[3].hasStarted){
//				return true;
//			}
//		}
//		return false;
//	}
	/**
	 * calculates the time for...
	 */
	public void calculateTime(){}
}
