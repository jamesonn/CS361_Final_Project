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
	public static float seconds;
	private static String SysTime;
	private static Log eventLog = new Log();
	
	public static void main(String args[]){
		File instructions;
		Scanner instructionParser;
		ArrayList<String> instructionLines = new ArrayList<String>();
		Timer timer = new Timer();
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
		
		for(int i = 0; i < instructionLines.size(); i++){
			String[] timeCommand = instructionLines.get(i).split("\t");
			String[] time = timeCommand[0].split(":");
			String[] commands = timeCommand[1].split(" ");
			hours = Integer.parseInt(time[0]);
			minutes = Integer.parseInt(time[1]);
			seconds = Float.parseFloat(time[2]);
			SysTime = ""+hours+":"+minutes+":"+seconds+" ";
			switch (commands[0]){
				case "TIME":{
					if (systemOn){
						timer.setTime(hours, minutes, seconds);
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
						timer = new Timer();
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
						String eventType = SysTime +" Event "+ commands[1];
						eventLog.addEvent(eventType);
						eventRunning = true;
						//eventLog.setPrintStart();
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
					if (systemOn){
						timer.addNum(Integer.parseInt(commands[1]));
					} break;
				}
				case "TRIG":{
					if (systemOn){
						if(sensors[Integer.parseInt(commands[1])-1] != null){
								if(Integer.parseInt(commands[1]) == 1){
									timer.setTime(hours, minutes, seconds);
									timer.start(Integer.parseInt(commands[1]));
	 							}
								if(Integer.parseInt(commands[1])-1 == 2){
									timer.setTime(hours, minutes, seconds);
									eventLog.addEvent(timer.stop(Integer.parseInt(commands[1])-1));
	 							}
							
						}
					} break;
				}
				case "START":{
					if (systemOn){ 
						//Sprint 2; "shorthand for TRIG 1"
						if(sensors[Integer.parseInt(commands[1])-1] != null){
							if(Integer.parseInt(commands[1])-1 == 1){
								timer.setTime(hours, minutes, seconds);
								timer.start(Integer.parseInt(commands[1])-1);
 							}
						}
					}break;
				}case "FINISH":{
					if (systemOn){ 
						//Sprint 2; "shorthand for TRIG 2"
						if(sensors[Integer.parseInt(commands[1])-1] != null){
							if(Integer.parseInt(commands[1])-1 == 2){
								timer.setTime(hours, minutes, seconds);
								eventLog.addEvent(timer.stop(Integer.parseInt(commands[1])-1));
 							}
						}
					}break;
				}
				case "DNF":{
					if (systemOn){ 
						//TODO: Sprint 1; "next competitor to finish will not finish"
					}break;
				}
				case "CLR":{
					if (systemOn){ 
						//TODO: Sprint 2; "clear NUM as the next competitor" a.k.a. remove them from queue
					}break;
				}
				case "SWAP":{
					if (systemOn){ 
						//Sprint 2; "exchange next to competitors to finish in IND"
					}break;
				}
				case "PRINT":{
					if (systemOn){ 
						//TODO: determine if printer is on; see "Operation of Unit" on p4
						String[] log = eventLog.getEventLog();//make a copy
//						System.out.println(log[0]);
						for(int j = 0; j < log.length; ++j){
							System.out.println(log[j]);
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
						eventLog.clear();
						if(!eventRunning)
						eventRunning = true;
						else{
							eventLog = new Log();
							//TODO: report error? unclear instructions here
						}
					} break;
				}
				case "EXIT":{
					System.exit(0);
					break;
				}
			}			
		}
	}
}
