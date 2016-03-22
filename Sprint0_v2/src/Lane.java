import java.util.LinkedList;
import java.util.Queue;

/**
 * handles interactions between Event and Racers
 * @author Group 1
 */
public class Lane {
	private Queue<Racer> peps = new LinkedList<Racer>();
	Racer curRacer = null; 
	
	
	
	
	
	public void start(float t){
		
	}
	
	public Racer stop(float t){
		return curRacer;
	}
	
	public void addRacer(Racer r){
		peps.add(r);
	}
	
	public boolean isEmpty(){
		return peps.isEmpty();
	}
	
}
