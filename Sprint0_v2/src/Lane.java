import java.util.LinkedList;
import java.util.Queue;

/**
 * handles interactions between Event and Racers
 * @author Group 1
 */
public class Lane {
	private Queue<Racer> peps = new LinkedList<Racer>();
	private Racer curRacer = null; 
	
	public void start(float t){
		curRacer = peps.remove();
		curRacer.start(t);
	}
	
	public Racer stop(float t){
		curRacer.stop(t);
		return curRacer;
	}
	
	public void addRacer(Racer r){
		peps.add(r);
	}
	
	public boolean isEmpty(){
		return peps.isEmpty();
	}
	
}
