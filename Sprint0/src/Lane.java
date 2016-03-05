import java.util.ArrayList;

/**
 * Determines is a lane has started
 * @author Group 1
 */
public class Lane {
	boolean hasStarted = false;
	//ArrayList<Racer> racers = new ArrayList<Racer>(); //potential queue situation
	
	public void setStarted(boolean started){
		hasStarted = started;
	}
	
	//remove once racers are stored in the lane we the trigger starting sets them in
	public boolean hasStarted(){
		return hasStarted;
	}
	
	/*public void addRacer(Racer racer){
		racers.add(racer);
	}*/
}
