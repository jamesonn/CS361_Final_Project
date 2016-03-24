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
	 */
	private void updateTime(float t){
		totalTime = t;
		//TODO convert
		String min, hr, sec = "";
		
	}
	
	/**
	 * update the total and currentSystem time from a string
	 * @param t
	 */
	private void updateTime(String t){
		curTime = t;
		//TODO convert
		String[] time = t.split(":");
		float tempTime = Float.parseFloat(time[2]);//add seconds
		tempTime += Float.parseFloat(time[1])*60 + Float.parseFloat(time[0])*120;
		totalTime = tempTime;
	}
	
	public void addRacer(int bib, float newT){
		updateTime(newT);
		Racer r = new Racer(bib, 1);
		lanes[0].addRacer(r);
	}
	
	public void trigger(int lane, float t){
		if(lane == 1 && !lanes[0].isEmpty()){
			lanes[0].start(t);
			//runner already start case
		}
		if(lane == 2 && !lanes[0].isEmpty()){
			Racer r = lanes[0].stop(t);
			String racerInfo = "blah";
			log.add(racerInfo);
			
		}
	}
	
	public ArrayList<String> print(){
		return log;
	}
}
