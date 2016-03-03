import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class ChronoTimer {
	public static int hours;
	public static int minutes;
	public static int seconds;

	Queue<Racer> chan1_2;
	Queue<Racer> chan3_4;
	Queue<Racer> chan5_6;
	Queue<Racer> chan7_8;
	
	//Beginning of parsing test data
	
	public static void main(String args[]){
		File instructions;
		Scanner instructionParser;
		ArrayList<String> instructionLines = new ArrayList<String>();
		
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
			String[] timeCommand = instructionLines.get(i).split(" ");
			String[] time = timeCommand[0].split(":");
			String[] commands = timeCommand[1].split(" ");
			hours = Integer.parseInt(time[0]);
			minutes = Integer.parseInt(time[1]);
			seconds = Integer.parseInt(time[2]);
		}

		//go through lines, determine actions to take (switch case maybe)
	}
}
