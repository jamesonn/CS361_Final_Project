import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class ChronoTimer {

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

		//go through lines, determine actions to take (switch case maybe)
	}
}
