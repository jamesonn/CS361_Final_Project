import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Takes input choice and either parses the test data or opens the GUI
 */
public class Client {

    private HttpURLConnection conn;
    private Log log;

    protected Client(boolean formatChoiceIsUI, String testFile) {
        log = new Log();
        ChronoTimer cTimer = new ChronoTimer(log);

        if(formatChoiceIsUI){
            UserInterface ui = new UserInterface(cTimer, log);
        }else {
            int hours;
            int minutes;
            double seconds;
            double TotalTime;
            String SysTime;

            File instructions;
            Scanner instructionParser;
            ArrayList<String> instructionLines = new ArrayList<>();

            try {
                instructions = new File(testFile);
                instructionParser = new Scanner(instructions);
                while (instructionParser.hasNextLine()) {
                    instructionLines.add(instructionParser.nextLine());
                }
                instructionParser.close();
            } catch (FileNotFoundException e1) {
                System.out.println("Something went wrong opening the test data");
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
                    System.out.println("Something went wrong parsing test data");
                }
            }
        }
    }

    public String sendData() {
        String urlSite = "http://localhost" + 8000;
        StringBuilder response = new StringBuilder();
        try {
            URL site = new URL(urlSite);
            conn = (HttpURLConnection) site.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes("data=" + 8000);
            out.flush();
            out.close();
            InputStreamReader inputStr = new InputStreamReader(conn.getInputStream());

            // read the characters from the request byte by byte and build up
            // the Response
            int nextChar;
            while ((nextChar = inputStr.read()) > -1) {
                response = response.append((char) nextChar);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }
}
