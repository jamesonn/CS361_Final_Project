import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * contains main method; handles parsing of input 
 * @author Group 1
 */
public class ChronoTimer {
	public static int hours;
	public static int minutes;
	public static double seconds;
	private static double TotalTime;
	private static String SysTime;
	protected static Event event;
	
	/**
	 * handles parsing and switch case
	 * @param args
	 */
	public static void main(String args[]){
		File instructions;
		Scanner instructionParser;
		ArrayList<String> instructionLines = new ArrayList<String>();
		//Timer timer = new Timer();
		boolean systemOn = true;  
		boolean eventRunning = false; //instructions treat this as a class, perhaps solution to tracking what type of event is happening?
		Sensor[] sensors = new Sensor[8];
		
		try{
			instructions = new File("src/Sprint1_Test.txt");
			instructionParser = new Scanner(instructions);
			while(instructionParser.hasNextLine()){
				instructionLines.add(instructionParser.nextLine());
			}
			instructionParser.close();
		}catch(FileNotFoundException e1){
			
		}
		
		/**
		 * begin switch case parsing;
		 * SysTime is string version
		 */
		for(int i = 0; i < instructionLines.size(); i++){
			String[] timeCommand = instructionLines.get(i).split("\t");
			String[] time = timeCommand[0].split(":");
			String[] commands = timeCommand[1].split(" ");
			hours = Integer.parseInt(time[0]);
			minutes = Integer.parseInt(time[1]);
			seconds = Double.parseDouble(time[2]);
			TotalTime = seconds + minutes*60 + hours*120;
			SysTime = ""+hours+":"+minutes+":"+seconds+" ";
			switch (commands[0]){
				case "TIME":{
					if (systemOn){
						event = new Event(SysTime);
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
						event = new Event(SysTime);
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
						//Sprint 2; need a way to tell the system what type of event to handle 
						String eventType = commands[1];
						switch(eventType){
							case "IND":{//no change necessary, default case
								break;
							}
							case "PARIND":{
								event = new PARIND(SysTime);
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
						event.addRacer(Integer.parseInt(commands[1]), TotalTime);
					} break;
				}
				case "TRIG":{
					if (systemOn  && eventRunning){
						if(sensors[Integer.parseInt(commands[1])-1] != null){
							event.trigger(Integer.parseInt(commands[1])-1, TotalTime);
						}
					} break;
				}
				case "START":{
					if (systemOn && eventRunning){ //Sprint 2; "shorthand for TRIG 1"
						if(sensors[0] != null){
						//TODO: DOES THIS NEED TO BE A SWITCH CASE???? Or does it litterally just mean trig 1?
							//in GRP trig 1 starts all lanes, in PARIND this would start 1 & 3
							event.trigger(3, TotalTime);
							event.trigger(1, TotalTime);
							//trig 3 MUST be before trig 1 to allow PARIND start otherwise
							//would case a false-finish for GRP events
						}
					}break;
				}case "FINISH":{
					if (systemOn && eventRunning){ //Sprint 2; "shorthand for TRIG 2"
						if(sensors[1] != null){
							//TODO: DOES THIS NEED TO BE A SWITCH CASE????
							event.trigger(2, TotalTime);
							
						}
					}break;
				}
				case "DNF":{
					if (systemOn && eventRunning){ //IND only???
						//Sprint 1; "next competitor to finish will not finish"
						event.didNotFinish();
					}break;
				}
				case "CLR":{
					if (systemOn && eventRunning){ 
						//TODO: Sprint 2; "clear NUM as the next competitor" a.k.a. remove them from queue
					//???
					}break;
				}
				case "SWAP":{
					if (systemOn && eventRunning){ 
						//Sprint 2; "exchange next two competitors to finish in IND type"
						event.swap();
					}break;
				}
				case "PRINT":{
					if (systemOn && eventRunning){ 
						//TODO: determine if printer is on; see "Operation of Unit" on p4
						ArrayList<String> log = event.print();
						//verify passed: System.out.println(log[0]);
						//j = getPrinterStartTime/Location, however we determine that
						for(int j = 0; j < log.size(); ++j){
							System.out.println(log.get(j));
						}
					} break;
				}
				case "EXPORT":{
					if (systemOn){ 
						//Sprint 2; "export run in XML to file RUN<RUN>"
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
							event = new Event(SysTime);
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
}
