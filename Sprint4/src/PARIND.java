import java.util.ArrayList;

/**
 * Handles two active lanes at once;
 * @author Group 1
 */
public class PARIND extends Event{
	
	/**
	 * Handles two active lanes at once;
	 * @param t
	 */
	public PARIND(String t) {
		super(t);
		runs.clear();
		lanes = new Lane[2];
		lanes[0] = new Lane();
		lanes[1] = new Lane();
		runs.add(curTime+ " PARIND");
		newRun();
	}
	
	@Override
	public void addRacer(int bib){
		Racer r = new Racer(bib);
		if(lanes[0].getNumRacers() <= lanes[1].getNumRacers())
			lanes[0].addRacer(r);
		else
			lanes[1].addRacer(r);
		//TODO: verify order of adding racers to lanes, currently adds to lane 1 first
	}
	
	@Override
	public void removeRacer(int bib){
		Racer temp = new Racer(bib);
		if(lanes[0].removeRacer(temp) != null){
			log.add(lanes[0].removeRacer(temp));
		}
		else if(lanes[1].removeRacer(temp) != null){
			log.add(lanes[1].removeRacer(temp));
		}
	}
	
	@Override
	public void trigger(int chan, double t){
		updateTime(t);
		switch(chan){
			case 1:{
				if(!lanes[0].isReadyEmpty()){
					lanes[0].start(t);
				}break;
			}
			case 2:{
				if(!lanes[0].isActiveEmpty()){
					log.add(lanes[0].stop(t));
				}break;
			}
			case 3:{
				if(!lanes[1].isReadyEmpty()){
					lanes[1].start(t);
				}break;
			}
			case 4:{
				if(!lanes[1].isActiveEmpty()){
					log.add(lanes[1].stop(t));
				}break;
			}
		}
	}
	
	@Override
	public ArrayList<String> print(double time){
		//log.addAll(lanes[0].print(time));
		//log.addAll(lanes[1].print(time));
		//return log;
		log.addAll(lanes[0].print(time));
		log.addAll(lanes[1].print(time));
		if(isActiveRun){
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(log.remove(0) + " In Progress");
			temp.addAll(log);
			log = temp;
		}
		runs.addAll(getLog());
//		for(int i =0; i < runs.size(); i++){
//			System.out.println(runs.get(i));
//		}//check for printing log
		return runs;
	}
	
}