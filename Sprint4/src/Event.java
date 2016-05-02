import java.util.ArrayList;

/**
 * Manages time, racer and lane information;
 * default is IND type event
 * @author Group 1
 */
public class Event {
	protected Lane[] lanes = new Lane[1];
	protected ArrayList<Racer> participants = new ArrayList<Racer>();
	//NOTE: line 0 is event name, rest records all the run info
	protected ArrayList<String> runs = new ArrayList<String>();
	//log handles current run only
	protected ArrayList<String> log = new ArrayList<String>();
	protected double totalTime = 0;
	protected static String curTime = "";
	protected int runNum = 1;//used in printing
	protected boolean isActiveRun = false;
	private Log cTimerLog;
	
	/**
	 * create an Event object setting the current time to t
	 * @param t
	 */
	public Event(String t, Log log){
        cTimerLog = log;
		lanes[0] = new Lane();
		updateTime(t);
		runs.add(curTime+ " IND");
		newRun();
	}

	public Event(String t){
		lanes[0] = new Lane();
		updateTime(t);
		runs.add(curTime+ " IND");
		newRun();
	}
	
	/**
	 * takes the current time and the Racer NUM, makes and adds a new Racer to the IND lane 
	 * @param bib
	 */
	public void addRacer(int bib){
		Racer r = new Racer(bib);
		participants.add(r);
		lanes[0].addRacer(r);
	}
	
	/**
	 * Takes the current time and the number triggered;  
	 * starts or stops based on number triggered;  if a stop adds finished Racer to log
	 * @param chan
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
			if(lanes[0].isActiveEmpty() && lanes[0].isReadyEmpty()){
				endRun(t);
			}
            cTimerLog.setLatestLine(lanes[0].getCurrentRacer().print() + " F");
		}
	}
	
	/**
	 * ENDRUN method, ends run & if a race actually finishes
	 * @return ArrayList<String>
	 */
	public ArrayList<String> endRun(double time){
		log.addAll(lanes[0].print(time));
		runs.addAll(getLog());
		log = new ArrayList<String>();
		isActiveRun = false;
		return print(time);
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
	public void removeRacer(int bib){
		Racer temp = new Racer(bib);
		if(participants.contains(temp)){
			log.add(lanes[0].removeRacer(temp));
			//System.out.println("in removeRacer method");
		}
		//System.out.println("outside if statement in RemoveRacer");
	}
	
	/**
	 * print the full record of every run in this event
	 * @param time
	 * @return ArrayList<String>
	 */
	public ArrayList<String> print(double time){
		log.addAll(lanes[0].print(time));
		if(isActiveRun){
			String a = log.get(0);
			String n = ""+(runNum-1);//as runNum was already ++
			if(a.endsWith(n)){
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(log.remove(0) + " In Progress");
				temp.addAll(log);
				log = temp;
			}
		}
		runs.addAll(getLog());
//		for(int i =0; i < runs.size(); i++){
//			System.out.println(runs.get(i));
//		}//check for printing log
		return runs;
	}
	
	/**
	 * start a new run in the current event
	 */
	public void newRun(){
		if(!isActiveRun){
			log.add(getCurTime()+" Run "+ runNum);
			isActiveRun = true;
			++runNum;
		}
		else{
			//this is illegal per functional requirements
		}
		
	}
	
	/**
	 * update the total and currentSystem time from a string
	 * @param t
	 */
	protected void updateTime(String t){
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
	protected void updateTime(double t){
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
	
	public ArrayList<Racer> getParticipants(){
		return participants;
		
	}
}
