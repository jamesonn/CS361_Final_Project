import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * handles interactions between the Event and the Racers
 * @author Group 1
 */
public class Lane {
	private Queue<Racer> ready = new LinkedList<Racer>();
	private LinkedList<Racer> active = new LinkedList<Racer>();
	private Racer curRacer;
	
	public void start(double t){
		curRacer = ready.remove();
		curRacer.start(t);
		active.add(curRacer);
	}
	
	// ?? make this return String with racer bib number and total time ??
	/**
	 * takes first Racer in the active queue and passes t as the Racers stop time;
	 * returns the string needed for the log
	 * @param t
	 * @return String
	 */
	public String stop(double t){
		curRacer = active.removeFirst();
		curRacer.stop(t);
		return curRacer.print();
	}
	
	/**
	 * adds a Racer to the ready queue
	 * @param r
	 */
	public void addRacer(Racer r){
		ready.add(r);
	}
	
	/**
	 * Swap the next two Racers to finish, those in position 0 and 1
	 */
	public void swap(){
		if (active.size() > 1 ) {
			Collections.swap(active, 0, 1);
		}
	}
	
	/**
	 * use if a Racer DNFs;  returns the log string for the DNF 
	 * Racer instead of calling the Racers print
	 * @return String
	 */
	public String didNotFinish(){
		curRacer = active.removeFirst();
		return curRacer.getBibNum() + " DNF";
	}
	
	/**
	 * is the queue of waiting Racers empty?
	 * @return boolean
	 */
	public boolean isReadyEmpty(){
		return ready.isEmpty();
	}
	/**
	 * is the queue of active Racers empty?
	 * @return boolean
	 */
	public boolean isActiveEmpty(){
		return active.isEmpty();
	}
}
