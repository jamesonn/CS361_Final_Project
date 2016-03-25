
/**
 * constructor requires racerNum and laneNum; 
 * knows start time, stop time, and total time
 * @author Group 1
 */
public class Racer {
	private int racerNum;
	private int laneNum;
	private double startTime;
	private double stopTime;
	
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
	
	public int getLaneNum(){
		return laneNum;	}
	
	public double getStartTime(){ 
		return startTime; }
	
	public void start(double st){ 
		startTime = st; }
	
	public double getEndTime(){
		return stopTime;	}
	
	public void stop(double st){
		stopTime = st;	}
	
	public double getTotalTime(){
		return stopTime-startTime;	}

}
