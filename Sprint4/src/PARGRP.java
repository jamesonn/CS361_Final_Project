import java.util.ArrayList;

public class PARGRP extends Event{
	/**
	 * Handles multiple groups at once
	 * @param t
	 */
	public PARGRP(String t) {
		super(t);
		runs.clear();
		lanes = new Lane[2];
		lanes[0] = new Lane();
		lanes[1] = new Lane();
		runs.add(curTime+ " PARGRP");
		newRun();
	}
	
	@Override
	public void addRacer(int bib){
		//TODO
	}
	
	
	@Override
	public void trigger(int chan, double t){
		//TODO
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
