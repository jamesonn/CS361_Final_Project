import java.util.ArrayList;

/**
 * Handles two active lanes at once;
 * @author Group 1
 */
public class PARIND extends Event{
	private Lane[] paraLane = new Lane[2];
	private ArrayList<String> paraLog = new ArrayList<String>();
	private double paraTotalTime = 0;
	private String paraCurTime = "";
	
	/**
	 * Handles two active lanes at once;
	 * @param t
	 */
	public PARIND(String t) {
		super(t);
		paraLane[0] = new Lane();
		paraLane[1] = new Lane();
		updateTime(t);
		paraLog.add(paraCurTime+ " PARIND");
	}
	
	@Override
	public void addRacer(int bib){
		Racer r = new Racer(bib);
		if(paraLane[0].getNumRacers() <= paraLane[1].getNumRacers())
			paraLane[0].addRacer(r);
		else
			paraLane[1].addRacer(r);
		//TODO: verify order of adding racers to lanes, currently adds to lane 1 first
	}
	
	@Override
	public void trigger(int chan, double t){
		updateTime(t);
		switch(chan){
			case 1:{
				if(!paraLane[0].isReadyEmpty()){
					paraLane[0].start(t);
				}break;
			}
			case 2:{
				if(!paraLane[0].isActiveEmpty()){
					paraLog.add(paraLane[0].stop(t));
				}break;
			}
			case 3:{
				if(!paraLane[1].isReadyEmpty()){
					paraLane[1].start(t);
				}break;
			}
			case 4:{
				if(!paraLane[1].isActiveEmpty()){
					paraLog.add(paraLane[1].stop(t));
				}break;
			}
		}
	}
	
	@Override
	public ArrayList<String> print(double time){
		paraLog.addAll(paraLane[0].print(time));
		paraLog.addAll(paraLane[1].print(time));
		return paraLog;
	}
	
	/**
	 * update the total and currentSystem time from a string
	 * @param t
	 */
	private void updateTime(String t){
		paraCurTime = t;
		String[] time = t.split(":");
		double tempTime = Float.parseFloat(time[2]);//add seconds
		tempTime += Float.parseFloat(time[1])*60 + Float.parseFloat(time[0])*3600;
		paraTotalTime = tempTime;
	}
	
	/**
	 * Update the total and currentSystem time from a float
	 * @param t
	 */
	private void updateTime(double t){
		paraTotalTime = t;
		int h, m;
		double s;
		h = (int) (t / 3600);
		t = t % 3600;
		m = (int) (t / 60);
		s = t % 60;
		paraCurTime = ""+h+":"+m+":"+ String.format("%.2f", s);
	}
}
