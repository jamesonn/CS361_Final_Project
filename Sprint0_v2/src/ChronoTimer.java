import com.google.gson.Gson;

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
	private ArrayList<Event> events;
	private int currentEvent;
	private Event event;
	
	/**
	 * ChronoTimer constructor
	 */
	public ChronoTimer(){
		systemOn = true;  
		eventRunning = false; //instructions treat this as a class, perhaps solution to tracking what type of event is happening?
		sensors = new Sensor[8];
	}

	/**
	 * switch case parsing of the commands; SysTime string version
	 * @param commands
	 * @param TotalTime
	 * @param SysTime
	 */
	public void executeCommand(String[] commands, double TotalTime, String SysTime)
	{
		switch (commands[0]){
			case "TIME":{
				if (systemOn){
					events.add(new Event(SysTime));
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
					sensors[deviceNum] = null;
				} break;
			}
			case "EVENT":{
				if (systemOn){
					currentEvent++;
					//Sprint 2; need a way to tell the system what type of event to handle 
					String eventType = commands[1];
					switch(eventType){
						case "IND":{//no change necessary, default case
							events.add(new Event(SysTime));
							break;
						}
						case "PARIND":{
							events.add(new PARIND(SysTime));
							break;
						}
						case "GRP":{
							break;
						}
						case "PARGRP":{
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
					events.get(currentEvent).addRacer(Integer.parseInt(commands[1]));
				} break;
			}
			case "TRIG":{
				if (systemOn  && eventRunning){
					/*if(sensors[Integer.parseInt(commands[1])-1] != null){
						events.get(currentEvent).trigger(Integer.parseInt(commands[1])-1, TotalTime);
					}*/
					if(sensors[Integer.parseInt(commands[1])-1] != null){
						events.get(currentEvent).trigger(Integer.parseInt(commands[1]), TotalTime);
					}
				} break;
			}
			case "START":{
				if (systemOn && eventRunning){ //Sprint 2; "shorthand for TRIG 1"
					if(sensors[0] != null){
					//TODO: DOES THIS NEED TO BE A SWITCH CASE???? Or does it literally just mean trig 1?
						//in GRP trig 1 starts all lanes, in PARIND this would start 1 & 3
						events.get(currentEvent).trigger(3, TotalTime);
						events.get(currentEvent).trigger(1, TotalTime);
						//trig 3 MUST be before trig 1 to allow PARIND start otherwise
						//would case a false-finish for GRP events
					}
				}break;
			}case "FINISH":{
				if (systemOn && eventRunning){ //Sprint 2; "shorthand for TRIG 2"
					if(sensors[1] != null){
						//TODO: DOES THIS NEED TO BE A SWITCH CASE????
						events.get(currentEvent).trigger(2, TotalTime);
						
					}
				}break;
			}
			case "DNF":{
				if (systemOn && eventRunning){ //IND only???
					//Sprint 1; "next competitor to finish will not finish"
					events.get(currentEvent).didNotFinish();
				}break;
			}
			case "CLR":{
				if (systemOn && eventRunning){ 
					//TODO: Sprint 2; "clear NUM as the next competitor" a.k.a. remove them from queue
					events.get(currentEvent).removeRacer();
				}break;
			}
			case "SWAP":{
				if (systemOn && eventRunning){ 
					//Sprint 2; "exchange next two competitors to finish in IND type"
					events.get(currentEvent).swap();
				}break;
			}
			case "PRINT":{
				//if (systemOn && eventRunning){ 
				if (systemOn){ 
					//TODO: determine if printer is on; see "Operation of Unit" on p4
					ArrayList<String> log = events.get(currentEvent).print(TotalTime);
					//verify passed: System.out.println(log[0]);
					//j = getPrinterStartTime/Location, however we determine that
					for(int j = 0; j < log.size(); j++){
						System.out.println(log.get(j));
					}
				} break;
			}
			case "EXPORT":{
				if (systemOn){
					Gson g = new Gson();
					g.toJson(events);
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
						if ( event.getClass().equals(PARIND.class)){
							events.add(new PARIND(SysTime));
						}
						else {
							events.add(new Event(SysTime));
						}
					}
					else{
						//TODO: reset or do nothing? unclear instructions here
					}
				} break;
			}
			case "EXIT":{
				System.exit(0);
				break;
			}
		}//end switch case
	}
}

