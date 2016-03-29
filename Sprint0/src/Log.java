/**
 * the event log used in print and export
 * @author Group 1
 */
public class Log {
	private String[] eventList;
	private int currentIndex, printStartPoint;

	
	public Log(){
		eventList = new String[2];
		currentIndex =  0;
		printStartPoint = 0;
	}
		
	// TODO work on return value
	/**
	 * take pre-made string and add to array 
	 * @param event
	 */
	public void addEvent(String event){
		resize();
		eventList[currentIndex] = event;
//		System.out.println(eventList[currentIndex]);
		currentIndex++;		
	}
	
	public void clear(){
		eventList = new String[2];
		currentIndex =  0;
		printStartPoint = 0;
	}
	
	
	/**
	 *  Set location where printer was turned on
	 */
	public void setPrintStart(){
		printStartPoint = currentIndex;
	}
	
	/**
	 * @return returns an array of strings 
	 */
	public String[] getEventLog(){
		return eventList;
	}
	
	/**
	 * @return position of the first event after the printer was turned on or 
	 * -1 if printer was not turned on
	 */
	public int getPrintStart(){
		return printStartPoint;
	}
	
	
	public void resetPrintPoint(){
		printStartPoint = -1;
	}
	
	private void resize(){		
		if (eventList.length < currentIndex + 1){			
			String[] array = new String[eventList.length * 2 + 1];
			
			for (int i = 0; i < eventList.length; i++){
				array[i] = eventList[i];
			}
			eventList = array;
		}
	}
	
}