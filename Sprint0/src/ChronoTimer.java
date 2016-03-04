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
	
	public static void main(String args[]){
		File instructions;
		Scanner instructionParser;
		ArrayList<String> instructionLines = new ArrayList<String>();
		Timer timer = new Timer();
		boolean systemOn = true;  
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
				case "EVENT":{
					if (systemOn){
						
					} break;
				}
				case "TOGGLE":{
					if (systemOn){
						sensors[Integer.parseInt(commands[1])-1].toggle();
					} break;
				}
				case "NUM":{
					if (systemOn){
						timer.addNum(Integer.parseInt(commands[1]));
					} break;
				}
				case "TRIG":{
					if (systemOn){
						if(sensors[Integer.parseInt(commands[1])-1].canTriggerSensor()){ //is sensor toggled on
							if(timer.hasLaneStarted(Integer.parseInt(commands[1]))){ //did this lane start or stop
								if(sensors[Integer.parseInt(commands[1])-1].isStartSensor){//if this is true, another racer has started in this lane before the previous finished
									
								}else{
									timer.stop(Integer.parseInt(commands[1]));
								}
							}else{
								timer.start(Integer.parseInt(commands[1]));
							}
						}
					} break;
				}
				case "DNF":{
					if (systemOn){ 
						
					}break;
				}
				case "PRINT":{
					if (systemOn){ 
						//for loop through all racers printing Number,
						//start time and end time? or just total time/DNF?
					} break;
				}
				case "ENDRUN":{
					if (systemOn){ 
						
					} break;
				}
				case"NEWRUN":{
					if (systemOn){ 
						
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
