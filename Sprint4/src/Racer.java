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
    private double totalTime;
	private ArrayList<String> runLog = new ArrayList<String>();
	
	public Racer(int bibNum){
		racerNum = bibNum;
	}

	public int getBibNum(){ 
		return racerNum; }
	
	public double getStartTime(){ 
		return startTime; }
	/**
	 * start a racer, pass in start time
	 * @param st
	 */
	public void start(double st){ 
		startTime = st; }
	
	public double getEndTime(){
		return stopTime;	}
	/**
	 * stop a racer, pass in end time
	 * @param st
	 */
	public void stop(double st){
		stopTime = st;	
		runLog.add(print());
	}
	/**
	 * find total time a racer was running for
	 * @return
	 */
	public double getTotalTime(){
        totalTime = stopTime - startTime;
		return totalTime;	}
	
	public ArrayList<String> getRunLog(){
		return runLog;
	}
	
	public String print(){
		double time = getTotalTime();
		String t = String.format("%.1f", time);
		//TODO: Handle race canceled midway through, i.e. stop() never called
		return ""+ racerNum +" "+ t;
	}
}
