import java.util.ArrayList;

/**
 * Created by Nate on 4/28/2016.
 */
public class Log {
    private ArrayList<String> runs = new ArrayList<>();
    private String lineByLine;

    public void logRun(String runData, int runNum){
        runs.add(runNum,runData);
    }

    public void setLineByLine(String line){
        lineByLine = line;
    }

    public String getRun(int run){
        return runs.get(run);
    }

    public String getLineByLine(){
        return lineByLine;
    }
}
