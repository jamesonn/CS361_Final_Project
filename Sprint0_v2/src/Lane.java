import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * handles interactions between Event and Racers
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
	public Racer stop(double t){
		curRacer = active.removeFirst();
		curRacer.stop(t);
		return curRacer;
	}
	
	public void addRacer(Racer r){
		ready.add(r);
	}
	
	public void swap(){
		Collections.swap(active, 0, 1);
	}
	
	public String didNotFinish(){
		curRacer = active.removeFirst();
		return curRacer.getBibNum() + " DNF";
	}
	
	public boolean isReadyEmpty(){
		return ready.isEmpty();
	}
	
	public boolean isActiveEmpty(){
		return active.isEmpty();
	}
}
