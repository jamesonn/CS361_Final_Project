import java.util.ArrayList;

/**
 * Manages time, racer and lane information;
 * default is IND type event
 * @author Group 1
 */
public class Event {
	//TODO
	//lanes = array of queues?
	private Lane[] lanes;
	private ArrayList<String> log = new ArrayList<String>();
	private float totalTime = 0;
	private String curTime = "";
	
	/**
	 * create an Event object setting the current time to t
	 * @param t
	 */
	public Event(String t){
		lanes = new Lane[1];
		updateTime(t);
		log.add(curTime+ " IND");
	}
	
	/**
	 * Update the total and currentSystem time from a float
	 * @param t
	 *///TODO
	private void updateTime(float t){
		totalTime = t;
		String time = "";
//		float m, h, s, temp = 0;
//		t/60
	}
	
	/**
	 * update the total and currentSystem time from a string
	 * @param t
	 */
	private void updateTime(String t){
		curTime = t;
		String[] time = t.split(":");
		float tempTime = Float.parseFloat(time[2]);//add seconds
		tempTime += Float.parseFloat(time[1])*60 + Float.parseFloat(time[0])*120;
		totalTime = tempTime;
	}
	
	/**
	 * takes the current time and the Racer NUM, makes and adds a new Racer to the IND lane 
	 * @param bib
	 * @param newT
	 */
	public void addRacer(int bib, float newT){
		updateTime(newT);
		Racer r = new Racer(bib, 1);
		lanes[0].addRacer(r);
	}
	
	/**
	 * Takes the current time and the number triggered;  
	 * starts or stops based on number triggered;  if a stop adds finished Racer to log
	 * @param lane
	 * @param t
	 */
	public void trigger(int chan, float t){
		if(chan == 1 && !lanes[0].isReadyEmpty()){
			lanes[0].start(t);
			//runner already start case
		}
		if(chan == 2 && !lanes[0].isActiveEmpty()){
			log.add(lanes[0].stop(t));
			
		}
	}
	
	public ArrayList<String> print(){
		return log;
	}
}
