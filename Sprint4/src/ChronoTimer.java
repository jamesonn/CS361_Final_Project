import com.google.gson.Gson;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * handles parsing of input; interface to Events and Sensors
 * @author Group 1
 */
public class ChronoTimer {
    private HttpURLConnection conn;
	private boolean systemOn;
	private boolean eventRunning;
    private boolean isPrinterOn;
    private boolean eventCommandCalled;
	private Sensor[] sensors;
	//events start at 1 not 0
	private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<String> eventPrintLines;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    private String runData;
    private String systemTime;
	private int currentEvent;
    private Log log;
	
	/**
	 * ChronoTimer constructor
	 */
	public ChronoTimer(Log log) {
        this.log = log;
        systemOn = true;
        isPrinterOn = false;
        eventCommandCalled = false;
        currentEvent = 0;
        eventRunning = true;
        sensors = new Sensor[8];
        Calendar.getInstance();
        systemTime = sdf.format(new Date());
        events.add(new Event(systemTime, log));
    }

	/**
	 * switch case parsing of the commands; SysTime string version
	 * @param commands Array holding string command in [0], then appropriate numbers in [1] or [2]
	 * @param totalTime Total time of racer when executeCommand is called
	 * @param sysTime Current time when executeCommand is called
	 */
	public void executeCommand(String[] commands, double totalTime, String sysTime)
	{
        String sensorType = "";
        int sensorNum = 0;
        String eventType = "";
        int commandNumber = 0;
        String commandEntered = commands[0];

        if(commandEntered.equals("CONN")){
            sensorType = commands[1];
            sensorNum = Integer.parseInt(commands[2]);
        }else if (commandEntered.equals("EVENT")){
            eventType = commands[1];
        }else if(commands.length > 1 && commands[1] != null && !commandEntered.equals("TIME")){
            commandNumber = Integer.parseInt(commands[1]);
        }

		switch (commandEntered){
			case "TIME":{
				if (systemOn){
					events.add(new Event(sysTime, log));
				} break;
			}
			case "ON":{
				systemOn = true;
				break;
			}
			case "OFF":{
				if (systemOn){
					systemOn = false;
				} break;
			}
			case "RESET":{
				if (systemOn){
					currentEvent = 0;
					//event = new Event(SysTime);
				} break;
			}
			case "CONN":{
				if (systemOn){
					sensors[sensorNum-1] = new Sensor(sensorType,sensorNum);
				} break;
			}
			case "DISC":{
				if (systemOn){
					sensors[commandNumber-1] = null;
				} break;
			}
			case "EVENT":{
				if (systemOn){
                    if(currentEvent == 0 && !eventCommandCalled){
                        currentEvent++;
                    }
                    eventCommandCalled = true;
					//Sprint 2; need a way to tell the system what type of event to handle
					switch(eventType){
						case "IND":{//no change necessary, default case
							events.add(new Event(sysTime, log));
							break;
						}
						case "PARIND":{
							events.add(new PARIND(sysTime, log));
							break;
						}
						case "GRP":{
							events.add(new GRP(sysTime, log));
							break;
						}
						case "PARGRP":{
							events.add(new PARGRP(sysTime, log));
							break;
						}
					}
					eventRunning = true;
				} break;
			}
			case "TOGGLE":{
				if (systemOn){
					if(sensors[commandNumber-1] != null){
						sensors[commandNumber-1].toggle();
					}
				} break;
			}
			case "NUM":{
				if (systemOn  && eventRunning){
					events.get(currentEvent).addRacer(commandNumber);
					log.setLatestLine(commands[1] + " " + sysTime);
				} break;
			}
			case "TRIG":{
				if (systemOn  && eventRunning){
					/*if(sensors[Integer.parseInt(commands[1])-1] != null){
						events.get(currentEvent).trigger(Integer.parseInt(commands[1])-1, TotalTime);
					}*/
                    Event runningEvent = events.get(currentEvent);
					if(sensors[commandNumber-1] != null){
						if (runningEvent instanceof PARGRP){
							((PARGRP) runningEvent).whichSensors(sensors);
							runningEvent.trigger(commandNumber, totalTime);
//							((PARGRP) runningEvent).trigger(commandNumber, totalTime, sensors);
						}
						else{
							runningEvent.trigger(commandNumber, totalTime);
						}
					}
				}
                break;
			}
			case "START":{
				if (systemOn && eventRunning){ //Sprint 2; "shorthand for TRIG 1"
					if(sensors[0] != null){
					//in GRP & PARGRP trig 1 starts all lanes, in PARIND this would start 1 & 3
						events.get(currentEvent).trigger(3, totalTime);
						events.get(currentEvent).trigger(1, totalTime);
					//trig 3 MUST be before trig 1 to allow PARIND start otherwise
					//would case a false-finish for GRP/PARGRP events
					}
				}break;
			}case "FINISH":{
				if (systemOn && eventRunning){ //Sprint 2; "shorthand for TRIG 2"
					if(sensors[1] != null){
						//DOES THIS NEED TO BE A SWITCH CASE????
						events.get(currentEvent).trigger(2, totalTime);
						
					}
				}break;
			}
			case "DNF":{
				if (systemOn && eventRunning){ //IND only???
					//Sprint 1; "next competitor to finish will not finish"
					events.get(currentEvent).didNotFinish();
				}break;
			}
			case "CLR":{
				if (systemOn && eventRunning){ 
					//Sprint 2; "clear NUM as the next competitor" a.k.a. remove them from queue
					events.get(currentEvent).removeRacer(commandNumber);
				}break;
			}
			case "SWAP":{
				if (systemOn && eventRunning){ 
					//Sprint 2; "exchange next two competitors to finish in IND type"
					events.get(currentEvent).swap();
				}break;
			}
			case "PRINT":{
				//if (systemOn && eventRunning){ 
				if (systemOn){
                    runData = "";
					//TODO: determine if printer is on; see "Operation of Unit" on p4
					eventPrintLines = events.get(currentEvent).print(totalTime);
					//verify passed: System.out.println(eventPrintLines[0]);
					//j = getPrinterStartTime/Location, however we determine that
					for(int j = 0; j < eventPrintLines.size(); j++){
                        runData += eventPrintLines.get(j);
                        runData += "\n";
					}
                    log.logRun(runData,commandNumber);
				}
                break;
			}
            case "PRINTPWR":{
                if(isPrinterOn){
                    isPrinterOn = false;
                }else{
                    isPrinterOn = true;
                }
                break;
            }
			case "EXPORT":{
				if (systemOn){
					Gson g = new Gson();
					try{
						BufferedWriter writer = new BufferedWriter(new FileWriter("Export"+commands[1]+".json"));
						writer.write(g.toJson(events.get(currentEvent)));
						writer.close();
					}catch(Exception IOException){
						System.out.println("Export in chronotimer failure");
					}
				}break;
			}
			case "ENDRUN":{
				if (systemOn){
					eventRunning = false;
                    runData = "";
                    eventPrintLines = events.get(currentEvent).endRun(totalTime);
                    for(int j = 0; j < eventPrintLines.size(); j++){
                        runData += eventPrintLines.get(j);
                        runData += "\n";
                        System.out.println(eventPrintLines.get(j));
                    }
                    log.logRun(runData);
                    sendData(getJSON());
				} break;
			}


			case "NEWRUN":{
				if (systemOn && !eventRunning){
                    eventRunning = true;
                    if ( events.get(currentEvent).getClass().equals(PARIND.class)){
                        currentEvent++;
                        events.add(new PARIND(sysTime, log));
                    } else {
                        currentEvent++;
                        events.add(new Event(sysTime, log));
                    }
                     // increment run number
                    events.get(currentEvent).newRun();
				}
                break;
			}
			case "EXIT":{
				System.exit(0);
				break;
			}
		}
	}

    public boolean getPrinterStatus(){
        return isPrinterOn;
    }

    private String sendData(String data) {
        String urlSite = "http://localhost:8000/sendresults";
        StringBuilder response = new StringBuilder();
        try {
            URL site = new URL(urlSite);
            conn = (HttpURLConnection) site.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes("data=" + data);
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

	/**
	 * this specifically is referring to the racer list
	 * @return the json of ArrayList<Racer>
     */
	private String getJSON() {
		Gson g = new Gson();
		return g.toJson(events.get(currentEvent).getParticipants());
	}
}

