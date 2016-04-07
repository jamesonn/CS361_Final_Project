import java.util.ArrayList;

/**
 * Manages time, racer and lane information;
 * default is IND type event
 * @author Group 1
 */
public class Event {
	private Lane[] lanes = new Lane[1];
	private ArrayList<Racer> participants = new ArrayList<Racer>();
	//NOTE: runs starts at 1 not 0
	private ArrayList<ArrayList<String>> runs = new ArrayList<ArrayList<String>>();
	private ArrayList<String> log = new ArrayList<String>();
	private double totalTime = 0;
	private String curTime = "";
	private int runNum = 1;
	
	/**
	 * create an Event object setting the current time to t
	 * @param t
	 */
	public Event(String t){
		lanes[0] = new Lane();
		updateTime(t);
		log.add(curTime+ " IND");
	}
	
	/**
	 * takes the current time and the Racer NUM, makes and adds a new Racer to the IND lane 
	 * @param bib
	 * @param newT
	 */
	public void addRacer(int bib){
		Racer r = new Racer(bib);
		participants.add(r);
		lanes[0].addRacer(r);
	}
	
	/**
	 * Takes the current time and the number triggered;  
	 * starts or stops based on number triggered;  if a stop adds finished Racer to log
	 * @param lane
	 * @param t
	 */
	public void trigger(int chan, double t){
		updateTime(t);
		if(chan == 1 && !lanes[0].isReadyEmpty()){
			lanes[0].start(t);
			//runner already start case
		}
		if(chan == 2 && !lanes[0].isActiveEmpty()){
			log.add(lanes[0].stop(t));
			
		}
	}
	
	/**
	 * Swap the next two Racers to finish, those in position 0 and 1
	 */
	public void swap(){
		lanes[0].swap();
	}
	
	/**
	 * use if a Racer DNFs;  adds the log string for the DNF Racer
	 */
	public void didNotFinish(){
		log.add(lanes[0].didNotFinish());
	}
	
	/**
	 * handles CLR case; ;
	 */
	public void removeRacer(){
		log.add(lanes[0].removeRacer());
	}
	
	public ArrayList<String> print(double time){
		log.addAll(lanes[0].print(time));
		runs.add(getLog());
		return log;
	}
	
	/**
	 * start a new run in the current event
	 */
	public void newRun(){
		++runNum;
	}
	
	/**
	 * update the total and currentSystem time from a string
	 * @param t
	 */
	private void updateTime(String t){
		curTime = t;
		String[] time = t.split(":");
		double tempTime = Float.parseFloat(time[2]);//add seconds
		tempTime += Float.parseFloat(time[1])*60 + Float.parseFloat(time[0])*3600;
		totalTime = tempTime;
	}
	
	/**
	 * Update the total and currentSystem time from a float
	 * @param t
	 */
	private void updateTime(double t){
		totalTime = t;
		int h, m;
		double s;
		h = (int) (t / 3600);
		t = t % 3600;
		m = (int) (t / 60);
		s = t % 60;
		curTime = ""+h+":"+m+":"+ String.format("%.2f", s);
	}
	
	public String getCurTime(){
		return curTime;
	}
	
	public double getTotalTime(){
		return totalTime;
	}
	
	public ArrayList<String> getLog(){
		return log;
	}
}
