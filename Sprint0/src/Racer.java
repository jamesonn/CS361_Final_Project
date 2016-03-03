
public class Racer {
	int racerNum;
	int laneNum;
	int numLaps;
	double startTime;
	double stopTime;
	private double[] lapTimes = new double[numLaps];
	private int lapNum = 0;
	double totalTime;
	
	public Racer(int bn, int ln){
		racerNum = bn;
		laneNum = ln;
	}

	public int getBibNum(){ return racerNum; }
	public int getLaneNum(){ return laneNum; }
	public void changeLaneNum(int ln){ laneNum = ln; }
	public double getStartTime(){ return startTime; }
	public void setStartTime(double st){ startTime = st; }
	public double gettotalTime(){
		calcTotalTime();
		return totalTime;
	}
	public void setNumLaps(int ls){ numLaps = ls; }
	public void setLapTime(double t){
		lapTimes[lapNum] = t;
	}
	private void calcTotalTime(){
		double lastTime = lapTimes[lapTimes.length-1];
		totalTime = lastTime-startTime;
	}
	public String printLapTimes(){
		String s = "Start: "+ startTime +" \n";
		if(lapTimes.length == 1){
			s += "Finish: " + lapTimes[0];
		}
		else{
			for(int i =0; i < lapTimes.length-1; ++i){
				s += "Lap #"+ (i+1)+": "+ lapTimes[i]+ "\n";
			}
			s += "Finish: " + lapTimes[lapTimes.length-1];
		}
		return s;
	}	
}
