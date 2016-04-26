import java.util.ArrayList;

public class PARGRP extends Event{
	/**
	 * Handles multiple groups at once
	 * @param t
	 */
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
	
	@Override
	public void trigger(int chan, double t){
		if(chan == 1 && !lanes[0].isReadyEmpty()){//start all
			for(int i = 0; i < 8; ++i){
				lanes[i].start(t);
			}
		}
		//TODO somehow check for pads connected
		else if(lanes[0].isReadyEmpty()){//already started bool?
			switch(chan){
				case 1:{
					if(!lanes[0].isActiveEmpty())
						lanes[0].stop(t);
					break;
				}
				case 2:{
					if(!lanes[1].isActiveEmpty())
						lanes[1].stop(t);
					break;
				}
				case 3:{
					if(!lanes[2].isActiveEmpty())
						lanes[2].stop(t);
					break;
				}
				case 4:{
					if(!lanes[3].isActiveEmpty())
						lanes[3].stop(t);
					break;
				}
				case 5:{
					if(!lanes[4].isActiveEmpty())
						lanes[4].stop(t);
					break;
				}
				case 6:{
					if(!lanes[5].isActiveEmpty())
						lanes[5].stop(t);
					break;
				}
				case 7:{
					if(!lanes[6].isActiveEmpty())
						lanes[6].stop(t);
					break;
				}
				case 8:{
					if(!lanes[7].isActiveEmpty())
						lanes[7].stop(t);
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
		return runs;
	}
	
}
