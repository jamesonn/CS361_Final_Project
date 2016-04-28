import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

/**
 * handles parsing of input; interface to Events and Sensors
 * @author Group 1
 */
public class ChronoTimer {
	private boolean systemOn;
	private boolean eventRunning;
	private Sensor[] sensors;
	//events start at 1 not 0
	private ArrayList<Event> events = new ArrayList<>();
	private int currentEvent;
    private Log log;
	
	/**
	 * ChronoTimer constructor
	 */
	public ChronoTimer(Log log){
        this.log = log;
		systemOn = true;
		currentEvent = 0;
		eventRunning = false; //instructions treat this as a class, perhaps solution to tracking what type of event is happening?
		sensors = new Sensor[8];
	}

	/**
	 * switch case parsing of the commands; SysTime string version
	 * @param commands Array holding string command in [0], then appropriate numbers in [1] or [2]
	 * @param totalTime Total time of racer when executeCommand is called
	 * @param sysTime Current time when executeCommand is called
	 */
	public void executeCommand(String[] commands, double totalTime, String sysTime)
	{
		switch (commands[0]){
			case "TIME":{
				if (systemOn){
					events.add(new Event(sysTime));
				} break;
			}
			case "ON":{
				systemOn = true;
				break;
			}
			case "OFF":{
				if (systemOn){
					systemOn = false;
				} break;
			}
			case "RESET":{
				if (systemOn){
					currentEvent = 0;
					//event = new Event(SysTime);
				} break;
			}
			case "CONN":{
				if (systemOn){
					sensors[Integer.parseInt(commands[2])-1] = new Sensor(commands[1],Integer.parseInt(commands[2]));
				} break;
			}
			case "DISC":{
				if (systemOn){
					int deviceNum = Integer.parseInt(commands[1]);
					sensors[deviceNum-1] = null;
				} break;
			}
			case "EVENT":{
				if (systemOn){
					currentEvent++;
					//Sprint 2; need a way to tell the system what type of event to handle 
					String eventType = commands[1];
					switch(eventType){
						case "IND":{//no change necessary, default case
							events.add(new Event(sysTime));
							break;
						}
						case "PARIND":{
							events.add(new PARIND(sysTime));
							break;
						}
						case "GRP":{
							events.add(new GRP(sysTime));
							break;
						}
						case "PARGRP":{
							events.add(new PARGRP(sysTime));
							break;
						}
					}
					eventRunning = true;
				} break;
			}
			case "TOGGLE":{
				if (systemOn){
					if(sensors[Integer.parseInt(commands[1])-1] != null){
						sensors[Integer.parseInt(commands[1])-1].toggle();
					}
				} break;
			}
			case "NUM":{
				if (systemOn  && eventRunning){
					events.get(currentEvent-1).addRacer(Integer.parseInt(commands[1]));
					log.setLatestLine(commands[1] + " " + sysTime);
				} break;
			}
			case "TRIG":{
				if (systemOn  && eventRunning){
					/*if(sensors[Integer.parseInt(commands[1])-1] != null){
						events.get(currentEvent-1).trigger(Integer.parseInt(commands[1])-1, TotalTime);
					}*/
					if(sensors[Integer.parseInt(commands[1])-1] != null){
						Event runningEvent = events.get(currentEvent-1);
						if (runningEvent instanceof PARGRP){
							((PARGRP) runningEvent).trigger(Integer.parseInt(commands[1]), totalTime, sensors);
						}
						else{
							runningEvent.trigger(Integer.parseInt(commands[1]), totalTime);
						}
					}
				} break;
			}
			case "START":{
				if (systemOn && eventRunning){ //Sprint 2; "shorthand for TRIG 1"
					if(sensors[0] != null){
					//in GRP & PARGRP trig 1 starts all lanes, in PARIND this would start 1 & 3
						events.get(currentEvent-1).trigger(3, totalTime);
						events.get(currentEvent-1).trigger(1, totalTime);
					//trig 3 MUST be before trig 1 to allow PARIND start otherwise
					//would case a false-finish for GRP/PARGRP events
					}
				}break;
			}case "FINISH":{
				if (systemOn && eventRunning){ //Sprint 2; "shorthand for TRIG 2"
					if(sensors[1] != null){
						//DOES THIS NEED TO BE A SWITCH CASE????
						events.get(currentEvent-1).trigger(2, totalTime);
						
					}
				}break;
			}
			case "DNF":{
				if (systemOn && eventRunning){ //IND only???
					//Sprint 1; "next competitor to finish will not finish"
					events.get(currentEvent-1).didNotFinish();
				}break;
			}
			case "CLR":{
				if (systemOn && eventRunning){ 
					//Sprint 2; "clear NUM as the next competitor" a.k.a. remove them from queue
					events.get(currentEvent-1).removeRacer(Integer.parseInt(commands[1]));
				}break;
			}
			case "SWAP":{
				if (systemOn && eventRunning){ 
					//Sprint 2; "exchange next two competitors to finish in IND type"
					events.get(currentEvent-1).swap();
				}break;
			}
			case "PRINT":{
				//if (systemOn && eventRunning){ 
				if (systemOn){
                    String runData = "";
					//TODO: determine if printer is on; see "Operation of Unit" on p4
					ArrayList<String> eventPrintLines = events.get(currentEvent-1).print(totalTime);
					//verify passed: System.out.println(eventPrintLines[0]);
					//j = getPrinterStartTime/Location, however we determine that
					for(int j = 0; j < eventPrintLines.size(); j++){
                        runData += eventPrintLines.get(j);
                        runData += "\n";
						System.out.println(eventPrintLines.get(j));
					}
                    log.logRun(runData,Integer.parseInt(commands[1]));
				} break;
			}
			case "EXPORT":{
				if (systemOn){
					Gson g = new Gson();
					try{
						BufferedWriter writer = new BufferedWriter(new FileWriter("Export"+commands[1]+".json"));
						writer.write(g.toJson(events));
						writer.close();
					}catch(Exception IOException){
						System.out.println("Export in chronotimer failure");
					}
				}break;
			}
			case "ENDRUN":{
				if (systemOn){ 
					eventRunning = false;
				} break;
			}

			case "NEWRUN":{
				if (systemOn){

					if(!eventRunning){
						eventRunning = true;
						if ( events.get(currentEvent-1).getClass().equals(PARIND.class)){
							currentEvent++;
							events.add(new PARIND(sysTime));
						}
						else {
							currentEvent++;
							events.add(new Event(sysTime));
						}
					}
					else{ // increment run number
						events.get(currentEvent-1).newRun();
						
					}
				} break;
			}
			case "EXIT":{
				System.exit(0);
				break;
			}
		}//end switch case
	}//end method
}//end class

