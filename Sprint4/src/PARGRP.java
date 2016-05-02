import java.util.ArrayList;

public class PARGRP extends Event{
	private Sensor[] activeSensors = new Sensor[1]; 
	
	/**
	 * Handles multiple groups at once
	 * @param t
	 */
	public PARGRP(String t, Log log) {
		super(t, log);
		runs.clear();
		//all eight in one lane or 8 lanes?
		//lanes[0] = new Lane();
		lanes = new Lane[8];
		for(int i = 0; i < 8; ++i){
			lanes[i] = new Lane();
		}
		updateTime(t);
		runs.add(curTime+ " PARGRP");
		newRun();
	}

	public PARGRP(String t) {
		super(t);
		runs.clear();
		//all eight in one lane or 8 lanes?
		//lanes[0] = new Lane();
		lanes = new Lane[8];
		for(int i = 0; i < 8; ++i){
			lanes[i] = new Lane();
		}
		updateTime(t);
		runs.add(curTime+ " PARGRP");
		newRun();
	}

	@Override
	public void addRacer(int bib){
		Racer r = new Racer(bib);
		if(participants.size() < 8){
			//add racer
			participants.add(r);
			for(int i=0; i < 8; ++i){
				if(lanes[i].getNumRacers() != 1){
					lanes[i].addRacer(r);
					break;
				}
			}
		}
		else{
			//not allowed
		}
	}
	
	public void whichSensors(Sensor[] sensors){
		if(sensors != null){
			activeSensors = new Sensor[sensors.length];
			for(int i=0; i < sensors.length; ++i){
				activeSensors[i] = sensors[i];
			}
		}
		else
;			//we are screwed
	}
	
	@Override
	public void trigger(int chan, double t){
		if(chan == 1 && !lanes[0].isReadyEmpty()){//start all
			for(int i = 0; i < 8; ++i){
				lanes[i].start(t);
				if(!activeSensors[i].canTriggerSensor()){
					log.add(lanes[i].didNotFinish());
				}
			}
		}
		//TODO somehow check for pads connected
		else if(lanes[0].isReadyEmpty()){//already started bool?
			switch(chan){
				case 1:{
					if(!lanes[0].isActiveEmpty())
						log.add(lanes[0].stop(t));
					break;
				}
				case 2:{
					if(!lanes[1].isActiveEmpty())
						log.add(lanes[1].stop(t));
					break;
				}
				case 3:{
					if(!lanes[2].isActiveEmpty())
						log.add(lanes[2].stop(t));
					break;
				}
				case 4:{
					if(!lanes[3].isActiveEmpty())
						log.add(lanes[3].stop(t));
					break;
				}
				case 6:{
					if(!lanes[5].isActiveEmpty())
						log.add(lanes[5].stop(t));
					break;
				}
				case 7:{
					if(!lanes[6].isActiveEmpty())
						log.add(lanes[6].stop(t));
					break;
				}
				case 8:{
					if(!lanes[7].isActiveEmpty())
						log.add(lanes[7].stop(t));
					break;
				}
			}//end switch
		}//end else
		
	}

	@Override
	public void removeRacer(int bib){
		Racer temp = new Racer(bib);
		boolean found = false;
		String racer = "";
		int i = 0;
		while(!found && i < 8){
			racer = lanes[i].removeRacer(temp);
			if(racer != null && racer.contains("CLR")){
				found = true;
			}
			++i;
		}
		if(found){ log.add(racer);}
	}
	
	
	@Override
	public ArrayList<String> print(double time){
		//TODO 
		//DNF cases prevent lane.print isActive loop
		//how do we determine in-progress vs endRun? isActiveRun?
		for(int i=0; i < 8 && activeSensors[i].canTriggerSensor(); ++i)
			log.addAll(lanes[i].print(time));
		if(isActiveRun){
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(log.remove(0) + " In Progress");
			temp.addAll(log);
			log = temp;
		}
		runs.addAll(getLog());
		for(int i =0; i < runs.size(); i++){
			System.out.println(runs.get(i));
		}//check for printing log
		return runs;
	}
	
}
