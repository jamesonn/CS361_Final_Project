
/**
 * constructor requires racerNum and laneNum; 
 * knows start time, stop time, and total time
 * @author Group 1
 */
public class Racer {
	private int racerNum;
	private int laneNum;
	private float startTime;
	private float stopTime;
	
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
	
	public float getStartTime(){ 
		return startTime; }
	
	public void start(float st){ 
		startTime = st; }
	
	public float getEndTime(){
		return stopTime;	}
	
	public void stop(float st){
		stopTime = st;	}
	
	public float getTotalTime(){
		return stopTime-startTime;	}

}
