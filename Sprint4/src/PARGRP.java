import java.util.ArrayList;

public class PARGRP extends Event{
	/**
	 * Handles multiple groups at once
	 * @param t
	 */
	public PARGRP(String t) {
		super(t);
		runs.clear();
		lanes[0] = new Lane();
		updateTime(t);
		runs.add(curTime+ " PARGRP");
		newRun();
	}
	
	@Override
	public void addRacer(int bib){
		//TODO
		if(participants.size() < 8){
			//add racer
		}
		else{
			//not allowed
		}
	}
	
	@Override
	public void trigger(int chan, double t){
		//TODO
		if(chan == 1 && !lanes[0].isReadyEmpty()){
			//start all
			
		}
		else if(!lanes[0].isActiveEmpty()){
			switch(chan){
				case 1:{
					
					break;
				}
				case 2:{
					
					break;
				}
				case 3:{
					
					break;
				}
				case 4:{
					
					break;
				}
				case 5:{
					
					break;
				}
				case 6:{
					
					break;
				}
				case 7:{
					
					break;
				}
				case 8:{
					
					break;
				}
			}
		}
		
	}

	@Override
	public void removeRacer(int bib){
		//TODO
	}
	
	
	@Override
	public ArrayList<String> print(double time){
		//TODO
		return runs;
	}
	
}
