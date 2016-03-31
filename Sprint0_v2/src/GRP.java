import java.util.ArrayList;

/**
 * Handles group events such as cross country runs.
 * @author Group 1
 */
public class GRP extends Event{
	private Lane[] lanes = new Lane[1];
	private ArrayList<String> log = new ArrayList<String>();
	private double totalTime = 0;
	private String curTime = "";
	
	/**
	 * Only one lane, overrides the trigger to start all but end individually
	 * @param t
	 */
	public GRP(String t) {
		super(t);
		lanes[0] = new Lane();
		updateTime(t);
		log.add(curTime+ " PARIND");
	}
	
	/**
	 * Only one lane, overrides the trigger to start all but end individually
	 */
	@Override
	public void trigger(int chan, double t){
		updateTime(t);
		if(chan == 1 && !lanes[0].isReadyEmpty()){
			while(!lanes[0].isReadyEmpty())
				lanes[0].start(t);
		}
		if(chan == 2 && !lanes[0].isActiveEmpty()){
			log.add(lanes[0].stop(t));
			
		}
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
		curTime = ""+h+":"+m+":"+ String.format("%.1f", s);
	}
	
	
	
}
/**all have the same start time. By convention the start
 *is on channel 1 and there are multiple finishes on channel 2,
 *but there is nothing particularly special about the events on 
 *each channel. When a start event is received, it is associated
 * with the start of the event. When a finish event is received,
 * it is associated with the next “place” – for example, the 
 * first finish is entered in the list of finishers with the 
 * first time, the next time with 2nd place, etc. There may be 
 * up to 9,999
*/
