import java.util.ArrayList;
import java.util.Iterator;

public class PARGRP extends Event{
	/**
	 * Handles multiple groups at once
	 * @param t
	 */
	public PARGRP(String t, Log log) {
		super(t, log);
		runs.clear();
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
		boolean foundIt = false;
		Iterator<Racer> pepsIt = participants.iterator();
		while(pepsIt.hasNext()){
			if(pepsIt.next().getBibNum() == bib){
				foundIt = true;
				break; //illegal
			}
		}
		if(!foundIt){
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
	}

	@Override
	public void trigger(int chan, double t){
		if(chan == 1 && !lanes[0].isReadyEmpty()){//start all
			for(int i = 0; i < 8; ++i){
				if(!lanes[i].isReadyEmpty()){
					lanes[i].start(t);
						log.add(lanes[i].didNotFinish());
				}
			}
		}
		//check for pads connected handled in ChronoTimer 
		else if(lanes[0].isReadyEmpty() && isActiveRun){
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
	public ArrayList<String> endRun(double time){
		for(int i=0; i < 8; ++i){
			//anyone left doesn't have an active channel, so DNF
			if(!lanes[i].isActiveEmpty())
				log.add(lanes[i].didNotFinish());
		}
		runs.addAll(getLog());
		log = new ArrayList<String>();
		isActiveRun = false;
		return print(time);
	}
	
	@Override
	public ArrayList<String> print(double time){
		//TODO 
		//DNF cases prevent lane.print isActive loop
		//how do we determine in-progress vs endRun? isActiveRun?
		for(int i=0; i < 8; ++i)
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
