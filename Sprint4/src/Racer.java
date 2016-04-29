import java.util.ArrayList;

/**
 * constructor requires racerNum and laneNum; 
 * knows start time, stop time, and total time
 * @author Group 1
 */
public class Racer{
	private int racerNum;
	private double startTime;
	private double stopTime;
	private ArrayList<String> runLog = new ArrayList<String>();
	
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
		stopTime = st;	
		runLog.add(print());
	}
	
	public double getTotalTime(){
		return stopTime-startTime;	}
	
	public String print(){
		double time = getTotalTime();
		String t = String.format("%.2f", time);
		//TODO: Handle race canceled midway through, i.e. stop() never called
		return ""+ racerNum +" "+ t;
	}
}
