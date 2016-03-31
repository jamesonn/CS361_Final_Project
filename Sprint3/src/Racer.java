/**
 * constructor requires racerNum and laneNum; 
 * knows start time, stop time, and total time
 * @author Group 1
 */
public class Racer {
	private int racerNum;
	private double startTime;
	private double stopTime;
	
	public Racer(int bibNum){
		racerNum = bibNum;
	}

	public int getBibNum(){ 
		return racerNum; }
	
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
	
	public String print(){
		double time = getTotalTime();
		String t = String.format("%.2f", time);
		return ""+ racerNum +" "+ t;
	}

}
