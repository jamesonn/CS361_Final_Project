public class Log {
	private String[] eventList;
	private int currentIndex, printStartPoint;

	
	public Log(){
		eventList = new String[10];
		currentIndex =  0;	
	}
		
	// TODO work on return value
	public void addEvent(String event){
		resize();
		eventList[currentIndex] = event;
		currentIndex++;		
	}
	
	// Set location where printer was turned on
	public void setPrintStart(){
		printStartPoint = currentIndex;
	}
	
	public String[] getEventLog(){
		return eventList;
	}
	
	/**
	 * @return position of the first event after the printer was turned on
	 */
	public int getPrintStart(){
		return printStartPoint;
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