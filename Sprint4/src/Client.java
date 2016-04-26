import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Nate on 4/7/2016.
 */
public class Client {

    public Client(boolean formatChoiceIsUI, String testfile) {

        ChronoTimer cTimer = new ChronoTimer();

        if(formatChoiceIsUI){
            UserInterface ui = new UserInterface(cTimer);
        }else {
            int hours;
            int minutes;
            double seconds;
            double TotalTime;
            String SysTime;

            File instructions;
            Scanner instructionParser;
            ArrayList<String> instructionLines = new ArrayList<String>();

            try {
                instructions = new File(testfile);
                instructionParser = new Scanner(instructions);
                while (instructionParser.hasNextLine()) {
                    instructionLines.add(instructionParser.nextLine());
                }
                instructionParser.close();
            } catch (FileNotFoundException e1) {
            }

            for (int i = 0; i < instructionLines.size(); i++) {
                try {
                    String[] timeCommand = instructionLines.get(i).split("\t");
                    String[] time = timeCommand[0].split(":");
                    String[] commands = timeCommand[1].split(" ");
                    hours = Integer.parseInt(time[0]);
                    minutes = Integer.parseInt(time[1]);
                    seconds = Double.parseDouble(time[2]);
                    TotalTime = seconds + minutes * 60 + hours * 3600;
                    SysTime = "" + hours + ":" + minutes + ":" + seconds + " ";

                    cTimer.executeCommand(commands, TotalTime, SysTime);
                } catch (Exception e) {

                }
            }
        }
    }
}
