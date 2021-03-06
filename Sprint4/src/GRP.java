import java.util.ArrayList;

/**
 * Handles group events such as cross country runs.
 * @author Group 1
 */
public class GRP extends Event{
	
	/**
	 * Only one lane, overrides the trigger to start all but end individually
	 * @param t
	 */
	public GRP(String t, Log log) {
		super(t, log);
		runs.clear();
		lanes[0] = new Lane();
		updateTime(t);
		runs.add(curTime+ " GRP");
		newRun();
	}

	public GRP(String t) {
		super(t);
		runs.clear();
		lanes[0] = new Lane();
		updateTime(t);
		runs.add(curTime+ " GRP");
		newRun();
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
			if(lanes[0].isActiveEmpty() && lanes[0].isReadyEmpty()){
				endRun(t);
			}
		}
	}
}