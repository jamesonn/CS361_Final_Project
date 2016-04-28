import java.util.ArrayList;

/**
 * Created by Nate on 4/28/2016.
 */
public class Log {
    private ArrayList<String> runs = new ArrayList<>();
    private String latestLine;
    private String history;

    public Log(){
        latestLine = "";
        history = "";
    }

    public void logRun(String runData, int runNum){
        runs.add(runNum,runData);
    }

    public void setLatestLine(String line){
        history += latestLine;
        history += "\n";
        latestLine = line;
    }

    public String getRun(int run){
        return runs.get(run);
    }

    public String getLatestLine(){
        return latestLine;
    }

    public String getHistory() { return history; }
}
