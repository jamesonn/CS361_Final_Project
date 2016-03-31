import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	public static void main(String args[]){
		ChronoTimer cTimer = new ChronoTimer();

		int hours;
		int minutes;
		double seconds;
		double TotalTime;
		String SysTime;

		File instructions;
		Scanner instructionParser;
		ArrayList<String> instructionLines = new ArrayList<String>();

		try{
			instructions = new File("Sprint2TestData2Fixed.txt");
			instructionParser = new Scanner(instructions);
			while(instructionParser.hasNextLine()){
				instructionLines.add(instructionParser.nextLine());
			}
			instructionParser.close();
		}catch(FileNotFoundException e1){}

		for(int i = 0; i < instructionLines.size(); i++){
			String[] timeCommand = instructionLines.get(i).split("\t");
			String[] time = timeCommand[0].split(":");
			String[] commands = timeCommand[1].split(" ");
			hours = Integer.parseInt(time[0]);
			minutes = Integer.parseInt(time[1]);
			seconds = Double.parseDouble(time[2]);
			TotalTime = seconds + minutes*60 + hours*3600;
			SysTime = ""+hours+":"+minutes+":"+seconds+" ";

			cTimer.executeCommand(commands, TotalTime, SysTime);
		}
	}
}
