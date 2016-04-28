import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class Testing {

	Racer racer, racer2, racer3, racer4;
	Sensor sensor;
	Lane lane;
	
	/**
	 * test racer constructor and getters, start() and stop()
	 */
	@Test
	public void testNewRacer(){
		racer = new Racer(111);
		//TODO: add test for runLog
		assertEquals(racer.getBibNum(), 111);
		assertEquals(racer.getStartTime(), 0.0, 0.0);
		assertEquals(racer.getEndTime(), 0.0, 0.0);
		assertEquals(racer.getTotalTime(),0.0,0.0);
		racer.start(50.0);
		racer.stop(300.00);
		assertEquals(racer.getStartTime(), 50.0, 0.0);
		assertEquals(racer.getEndTime(), 300.0, 0.0);
		assertEquals(racer.getTotalTime(), 250.0, 0.0);
	}
	
	/**
	 * test start sensor constructor and getters and toggle()
	 */
	@Test
	public void testSensor(){
		sensor = new Sensor("EYE", 1);
		assertEquals(sensor.getDeviceType(), "EYE");
		assertEquals(sensor.getSensorNumber(), 1);
		assertFalse(sensor.canTriggerSensor());
		sensor = new Sensor("GATE", 2);
		assertEquals(sensor.getDeviceType(), "GATE");
		assertEquals(sensor.getSensorNumber(), 2);
		assertFalse(sensor.canTriggerSensor());
		sensor.toggle();
		assertTrue(sensor.canTriggerSensor());
		
	}
	
	/**
	 * test lane, start racers 1-4, stop 1, swap 2&3, stop 3&2, DNF 4, check all total times
	 */
	@Test
	public void testLane(){
		lane = new Lane();
		racer = new Racer(111);
		racer2 = new Racer(222);
		racer3 = new Racer(333);
		racer4 = new Racer(444);
		assertTrue(lane.isReadyEmpty());
		assertTrue(lane.isActiveEmpty());
		lane.addRacer(racer);
		lane.addRacer(racer2);
		lane.addRacer(racer3);
		lane.addRacer(racer4);
		assertFalse(lane.isReadyEmpty());
		assertTrue(lane.isActiveEmpty());
		lane.start(100.0);	//racer start
		lane.start(200.0);	//racer2 start
		lane.start(300.0);	//racer3 start
		lane.start(400.0);  //racer4 start
		assertTrue(lane.isReadyEmpty());
		assertFalse(lane.isActiveEmpty());
		lane.stop(400.0);	//racer stop
		assertEquals(racer.getTotalTime(), 300.0, 0.0);
		lane.swap(); //swap racer2 and racer3
		lane.stop(500.0); //racer3 stop
		assertEquals(racer3.getTotalTime(), 200.0, 0.0);
		lane.stop(600.0);	//racer2 stop
		assertEquals(racer2.getTotalTime(), 400.0, 0.0);
		assertEquals(lane.didNotFinish(), "444 DNF");  //racer4 DNF
		assertTrue(lane.isActiveEmpty());
	}
	
	/**
	 * ensure the SysTime and TotalTime are updating correctly in Event
	 */
	@Test
	public void testUpdateTime(){
		Event e = new Event("11:14:30.0");
		assertEquals(e.getCurTime(), "11:14:30.0");
		assertEquals(e.getTotalTime(), 40470, 0.0);
		e.trigger(0, 40502);
		assertEquals(e.getCurTime(), "11:15:2.00");
		assertEquals(e.getTotalTime(), 40502, 0.0);
		e.trigger(0, 43199.9);
		assertEquals(e.getCurTime(), "11:59:59.90");
		assertEquals(e.getTotalTime(), 43199.9, 0.0);
		
	}
	
	/**
	 * test basic example of IND event
	 */
	@Test
	public void testNormalIND(){
		Event e = new Event("11:14:30.0");
		assertFalse(e.getLog().isEmpty());
		e.addRacer(111);
		e.addRacer(112);
		e.addRacer(113);		
		e.trigger(1, 40505.5);
		e.trigger(1, 40700.5);
		e.trigger(1, 40701);
		e.swap();
		e.trigger(2, 40928.3);//finish R2
		e.didNotFinish();//DNF R1, R3 in progress
		ArrayList<String> list = e.print(40950.0);
		assertEquals(5, list.size());
		assertEquals("11:14:30.0 IND", list.get(0));
		assertEquals("11:14:30.0 Run 1 In Progress", list.get(1));
		assertEquals("112 227.80 F", list.get(2));
		assertEquals("111 DNF", list.get(3));
		assertEquals("113 249.00 R", list.get(4));
		
	}
	
	/**
	 * test basic example of PARIND event
	 */
	@Test
	public void testNormalPARIND(){
		Event e = new PARIND("11:14:30.0");
		assertFalse(e.getLog().isEmpty());
		e.addRacer(111);
		e.addRacer(112);
		e.addRacer(113);
		e.addRacer(114);
		e.trigger(1, 40505.5); //start R1
		e.trigger(1, 40700.5); //start R3
		e.trigger(2, 40702.7);//finish R1
		e.trigger(3, 40710);  //start R2
		e.trigger(4, 40800.3);//finish R2
		e.trigger(3, 40810); //start R4
		//R3 and R4 should be in progress
		ArrayList<String> list = e.print(40900);
		assertEquals(6, list.size());
		assertEquals("11:14:30.0 PARIND", list.get(0));
		assertEquals("11:14:30.0 Run 1 In Progress", list.get(1));
		assertEquals("111 197.20 F", list.get(2));
		assertEquals("112 90.30 F", list.get(3));
		assertEquals("113 199.50 R", list.get(4));
		assertEquals("114 90.00 R", list.get(5));
		
		
	}
	
	/**
	 * test basic example of GRP event
	 */
	@Test
	public void testNormalGRP(){
		Event e = new Event("11:14:30.0");
		assertFalse(e.getLog().isEmpty());
		e.addRacer(111);
		e.addRacer(112);
		e.addRacer(113);
		e.addRacer(114);
		e.trigger(1, 40505.5); //start R1
		e.trigger(1, 40700.5); //do nothing
		e.trigger(2, 40702.7);//finish R1
		e.trigger(3, 40710);  //do nothing
		e.trigger(2, 40800.3);//finish R2
		e.trigger(4, 40810); //do nothing
		e.trigger(2, 40835.2);//finish R3
		//R3 and R4 should be in progress
		
		ArrayList<String> list = e.print(40900);
		assertEquals(6, list.size());
		assertEquals("11:14:30.0 GRP", list.get(0));
		assertEquals("11:14:30.0 Run 1 In Progress", list.get(1));
		assertEquals("111 197.20 F", list.get(2));
		assertEquals("112 90.30 F", list.get(3));
		assertEquals("113 199.50 F", list.get(4));
		assertEquals("114 90.00 R", list.get(5));
		
	}
	
	/**
	 * test basic example of PARGRP event
	 */
	@Test
	public void testNormalPARGRP(){
		Event e = new Event("11:14:30.0");
	}
	
	/**
	 * Tests CLR command methods removeRacer in both Event
	 * and Lane
	 */
	@Test
	public void testCLRracer(){
		lane = new Lane();
		lane.addRacer(new Racer(111));
		lane.addRacer(new Racer(222));
		lane.addRacer(new Racer(333));
		lane.addRacer(new Racer(444));
		lane.start(100.0);	//racer start
		lane.start(200.0);	//racer2 start
		lane.start(300.0);	//racer3 start
		lane.stop(400.0);	//racer stop
		ArrayList<String> laneList = lane.print(900);
		assertEquals(3, laneList.size());
		Racer take = new Racer(444);
		lane.removeRacer(take);
		laneList = lane.print(900);
		assertEquals(3, laneList.size());
		take = new Racer(222);
		lane.removeRacer(take);
		assertEquals(3, laneList.size());
		
		
		Event e = new Event("11:14:30.0");
		e.addRacer(114);
		e.trigger(1, 40505.5); //start R1
		ArrayList<String> eventList = e.print(40900);
		assertEquals(3, eventList.size());
		eventList = new ArrayList<String>();
		e.removeRacer(114);
		eventList = e.print(40900);
		//does not overwrite info if on same race
		assertEquals(3, eventList.size()); 
		assertEquals("114 CLR", eventList.get(2));
		
	}
}
