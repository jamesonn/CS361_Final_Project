import java.util.ArrayList;

import sun.misc.Queue;

/**
 * Determines is a lane has started
 * @author Group 1
 */
public class Lane {
	private boolean hasStarted = false;
	private Queue<Racer> racers = new Queue<Racer>();
	private Racer currentRacer;
	//ArrayList<Racer> racers = new ArrayList<Racer>(); //potential queue situation
	
	public void setStarted(boolean started){
		hasStarted = started;
	}
	
	//remove once racers are stored in the lane we the trigger starting sets them in
	/**
	 * 
	 * @return boolean
	 */
	public boolean hasStarted(){
		return hasStarted;
	}
	
	/**
	 * dequeues the FI racer and sets their start time
	 * @param time
	 */
	public void startRacer(float time){
		currentRacer = popRacer();
		currentRacer.setStartTime(time);
		hasStarted = true;
	}
	
	/**
	 * 
	 * @param time
	 * @return
	 */
	public Racer racerFinished(float time){
		currentRacer.setLapTime(time);
		return currentRacer;
	}
	
	
	/**
	 * adds a racer to the queue
	 * @param racer
	 */
	public void pushRacer(Racer racer){
		racers.enqueue(racer);
	}
	
	/**
	 * handles the dequeue try-catch
	 * @return FI racer
	 */
	public Racer popRacer(){
		Racer finisher = new Racer(0, 0);
		try {
			finisher = racers.dequeue();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finisher;
	}
}
