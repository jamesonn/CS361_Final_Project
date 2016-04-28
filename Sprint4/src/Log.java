import java.util.ArrayList;

/**
 * Created by Nate on 4/28/2016.
 */
public class Log {
    private ArrayList<String> runs = new ArrayList<>();
    private String latestLine;
    private String history;
    private int lastRun;

    public Log(){
        latestLine = "";
        history = "";
        lastRun = 0;
    }

    public void logRun(String runData){
        runs.add(runData);
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

    public String getRun() {
        lastRun++;
        return runs.get(lastRun - 1);
    }

    public String getLatestLine(){
        return latestLine;
    }

    public String getHistory() { return history; }
}
