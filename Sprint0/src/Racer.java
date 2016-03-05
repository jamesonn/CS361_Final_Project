
/**
 * constructor requires racerNum and laneNum; 
 * knows start time, laps, and total time
 * @author Group 1
 */
public class Racer {
	int racerNum;
	int laneNum;
	int numLaps = 1;
	float startTime;
	float stopTime;
	private float[] lapTimes = new float[numLaps];
	private int lapNum = 0;
	double totalTime;
	
	/**
	 * @param bibNum
	 * @param laneNum
	 */
	public Racer(int bibNum, int laneNum){
		racerNum = bibNum;
		this.laneNum = laneNum;
	}

	public int getBibNum(){ 
		return racerNum; }
	
	public double getStartTime(){ 
		return startTime; }
	
	public void setStartTime(float st){ 
		startTime = st; }
	
	public double getTotalTime(){
		calcTotalTime();
		return totalTime;
	}
	public void setNumLaps(int ls){ 
		numLaps = ls; 
		lapTimes = new float[numLaps];
	}
	public void setLapTime(float t){
		lapTimes[lapNum] = t;
	}
	/**
	 * Retrieves final lap time and subtracts
	 * it from the start time to get the total time.  
	 */
	private void calcTotalTime(){
		if(numLaps == 1){
			lapTimes[lapTimes.length-1] = stopTime;
		}
		System.out.println("racer end time "+lapTimes[lapTimes.length-1]);
		System.out.println("racer start time "+startTime);
		float lastTime = lapTimes[lapTimes.length-1];
		totalTime = lastTime-startTime;
	}
	/**
	 * prints start on first line, each following lap time
	 * and ends with the final lap time
	 * @return string
	 */
	public String printLapTimes(){
		String s = "Start: "+ startTime +" \n";
		if(lapTimes.length == 1){
			s += "Finish: " + lapTimes[0];
		}
		else{
			for(int i =0; i < lapTimes.length-1; ++i){
				s += "Lap #"+ (i+1)+": "+ lapTimes[i]+ "\n";
			}
			s += "Finish: " + lapTimes[lapTimes.length-1];
		}
		return s;
	}
}
