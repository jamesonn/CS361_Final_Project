
/**
 * constructor requires racerNum and laneNum; 
 * knows start time, laps, and total time
 * @author Group 1
 */
public class Racer {
	int racerNum;
	int laneNum;
	int numLaps;
	double startTime;
	double stopTime;
	private double[] lapTimes = new double[numLaps];
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
	
	public void setStartTime(double st){ 
		startTime = st; }
	
	public double gettotalTime(){
		calcTotalTime();
		return totalTime;
	}
	public void setNumLaps(int ls){ 
		numLaps = ls; 
		lapTimes = new double[numLaps];
	}
	public void setLapTime(double t){
		lapTimes[lapNum] = t;
	}
	/**
	 * Retrieves final lap time and subtracts
	 * it from the start time to get the total time.  
	 */
	private void calcTotalTime(){
		double lastTime = lapTimes[lapTimes.length-1];
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
