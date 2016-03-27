import java.util.ArrayList;

/**
 * Handles two active lanes at once;
 * @author Group 1
 */
public class PARIND extends Event{
	private Lane[] lanes;
	private ArrayList<String> log = new ArrayList<String>();
	private double totalTime = 0;
	private String curTime = "";
	
	/**
	 * Handles two active lanes at once;
	 * @param t
	 */
	public PARIND(String t) {
		super(t);
		lanes = new Lane[1];
		updateTime(t);
		log.add(curTime+ " PARIND");
	}
	
	@Override
	public void addRacer(int bib, double t){
		updateTime(t);
		Racer r = new Racer(bib, 1);
		if(lanes[0].getNumRacers() <= lanes[1].getNumRacers())
			lanes[0].addRacer(r);
		else
			lanes[1].addRacer(r);
		//TODO: verify order of adding racers to lanes
	}
	
	@Override
	public void trigger(int chan, double t){
		updateTime(t);
		switch(chan){
			case 1:{
				if(!lanes[0].isReadyEmpty()){
					lanes[0].start(t);
				}break;
			}
			case 2:{
				if(!lanes[0].isActiveEmpty()){
					log.add(lanes[0].stop(t));
				}break;
			}
			case 3:{
				if(!lanes[1].isReadyEmpty()){
					lanes[1].start(t);
				}break;
			}
			case 4:{
				if(!lanes[1].isActiveEmpty()){
					log.add(lanes[1].stop(t));
				}break;
			}
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
		tempTime += Float.parseFloat(time[1])*60 + Float.parseFloat(time[0])*120;
		totalTime = tempTime;
	}
	
	/**
	 * Update the total and currentSystem time from a float
	 * @param t
	 */
	private void updateTime(double t){
		totalTime = t;
		double m, h, s, temp = 0;
		h= findHour(t/60, 0, 23);//binary search for the hour?
		temp = t - h*120;
		m = findMinute(temp, 0, 59);
		s = temp - m*60;
		curTime = ""+h+":"+m+":"+s+" "; 
	}
	
	private int findHour(double t, int low, int high){
		if(low >= high)
			return low;
		if(t <= low*120){
			findHour(t, 0, low);
		}
		else
			findHour(t, low+1, high);
		return high;
	}
	
	private int findMinute(double t, int low, int high){
		if(low >= high)
			return low;
		if(t <= low*60){
			findHour(t, 0, low);
		}
		else
			findHour(t, low+1, high);
		return high;
	}
	
}
