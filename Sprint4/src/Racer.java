import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * constructor requires racerNum and laneNum; 
 * knows start time, stop time, and total time
 * @author Group 1
 */
public class Racer{
	private int racerNum;
	private double startTime;
	private double stopTime;
    private double totalTime;
	private ArrayList<String> runLog = new ArrayList<String>();
	private String name;
	
	public Racer(int bibNum){
		racerNum = bibNum;
		name = getNameFromFile(bibNum);
	}

	public int getBibNum(){ 
		return racerNum; }
	
	public double getStartTime(){ 
		return startTime; }
	/**
	 * start a racer, pass in start time
	 * @param st
	 */
	public void start(double st){ 
		startTime = st; }
	
	public double getEndTime(){
		return stopTime;	}
	/**
	 * stop a racer, pass in end time
	 * @param st
	 */
	public void stop(double st){
		stopTime = st;	
		runLog.add(print());
	}
	/**
	 * find total time a racer was running for
	 * @return
	 */
	public double getTotalTime(){
        totalTime = stopTime - startTime;
		return totalTime;	}
	
	public ArrayList<String> getRunLog(){
		return runLog;
	}
	
	public String print(){
		double time = getTotalTime();
		String t = String.format("%.1f", time);
		//TODO: Handle race canceled midway through, i.e. stop() never called
		return ""+ racerNum +" "+ t;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String newName){
		this.name = newName;
	}
	
	 private static String getNameFromFile(int bibCheck){
	    	File instructions;
	        Scanner instructionParser;
	        ArrayList<String> instructionLines = new ArrayList<>();

	        try {
	            instructions = new File("racers.txt");
	            instructionParser = new Scanner(instructions);
	            while (instructionParser.hasNextLine()) {
	                instructionLines.add(instructionParser.nextLine());
	            }
	            instructionParser.close();
	        } catch (FileNotFoundException e1) {
	            System.out.println("Something went wrong opening the racer file");
	        }
	        
	        for (int i = 0; i < instructionLines.size(); i++) {
	            try {
	            	String[] racerInfo = instructionLines.get(i).split("\t");
	                int bib = Integer.parseInt(racerInfo[0]);
	                if (bibCheck == bib) {
	                	return racerInfo[1];
	                }
	            } catch (Exception e) {
	                System.out.println("Something went wrong parsing test data");
	            }
	        }
	    	return "";  //no name found in file
	 }
}
