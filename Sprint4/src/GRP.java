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
		log.add(curTime+ " GRP");
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
}