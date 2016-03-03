import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class ChronoTimer {
	public static int hours;
	public static int minutes;
	public static float seconds;

	Queue<Racer> chan1_2;
	Queue<Racer> chan3_4;
	Queue<Racer> chan5_6;
	Queue<Racer> chan7_8;
	
	//Beginning of parsing test data
	
	public static void main(String args[]){
		File instructions;
		Scanner instructionParser;
		ArrayList<String> instructionLines = new ArrayList<String>();
		Timer timer = new Timer();
		
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
			String[] timeCommand = instructionLines.get(i).split("/t");
			String[] time = timeCommand[0].split(":");
			String[] commands = timeCommand[1].split(" ");
			hours = Integer.parseInt(time[0]);
			minutes = Integer.parseInt(time[1]);
			seconds = Float.parseFloat(time[2]);
			switch (commands[0]){
				case "TIME":{
					timer.setTime(hours, minutes, seconds);
					break;
				}
				case "ON":{
					break;
				}
				case "OFF":{
					break;
				}
				case "CONN":{
					break;
				}
				case "EVENT":{
					break;
				}
				case "TOGGLE":{
					break;
				}
				case "NUM":{
					timer.addNum(Integer.parseInt(commands[1]));
					break;
				}
				case "TRIG":{
					break;
				}
				case "PRINT":{
					break;
				}
				case "ENDRUN":{
					break;
				}
				case"NEWRUN":{
					break;
				}
				case "EXIT":{
					break;
				}
			}			
		}
	}
}
