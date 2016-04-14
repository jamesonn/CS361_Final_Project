import java.util.ArrayList;
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
	
	/**
	 * takes first Racer in the active queue and passes t as the Racers stop time;
	 * returns the string needed for the log
	 * @param t
	 * @return String
	 */
	public String stop(double t){
		curRacer = active.removeFirst();
		curRacer.stop(t);
		return curRacer.print() + " F";
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
	 * Clear a Racer from the competition
	 * @return
	 */
	public String removeRacer(int bib){
		if(!ready.contains(bib)) 
			return null;
		
		// if found		
		else{
			Queue<Racer> temp = new LinkedList<Racer>();
			String removed = "check lane removeRacer"; // should only print this if ready is empty
			
			while(!ready.isEmpty()){
				if(curRacer.getBibNum() != bib){
					// add to temp queue
					temp.add(curRacer);
				}
				else{
					removed = curRacer.getBibNum() + " CLR";
				}
			}
			ready = temp;
			return removed;
		}
	}
	
	
	
	public ArrayList<String> print(double t){
		ArrayList<String> list = new ArrayList<String>();
		double elapsed;
		
		LinkedList<Racer> tempActive = new LinkedList<Racer>();
		while(!isActiveEmpty()){
			curRacer = active.removeFirst();
			elapsed = t - curRacer.getStartTime();
			String e = String.format("%.2f", elapsed);
			list.add(curRacer.getBibNum()+" "+e+" R");
			tempActive.add(curRacer);
		}
		active = tempActive;
		
		Queue<Racer> tempReady = new LinkedList<Racer>();
		if(!isReadyEmpty()){
			curRacer = ready.remove();
			list.add(curRacer.getBibNum()+" "+curRacer.getStartTime()+" >");
			tempReady.add(curRacer);
		}
		while(!isReadyEmpty()){
			curRacer = ready.remove();
			list.add(curRacer.getBibNum()+" "+curRacer.getStartTime());
			tempReady.add(curRacer);
		}
		ready = tempReady;
		
		return list;
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
	
	/**
	 * returns the number of racers the Lane knows 
	 * it has in both active and ready
	 * @return int
	 */
	public int getNumRacers(){
		return ready.size() + active.size();
	}
}
