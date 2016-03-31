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
		assertTrue(sensor.isStartSensor());
		assertFalse(sensor.canTriggerSensor());
		sensor = new Sensor("GATE", 2);
		assertEquals(sensor.getDeviceType(), "GATE");
		assertEquals(sensor.getSensorNumber(), 2);
		assertFalse(sensor.isStartSensor());
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
		assertEquals(4, list.size());
		assertEquals(list.get(0), "11:14:30.0 IND");
		assertEquals(list.get(1), "112 227.80 F");
		assertEquals(list.get(2), "111 DNF");
		assertEquals(list.get(3), "113 249.00 R");
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
		assertEquals(5, list.size());
		assertEquals(list.get(0), "11:14:30.0 PARIND");
		assertEquals(list.get(1), "111 197.20 F");
		assertEquals(list.get(2), "112 90.30 F");
		assertEquals(list.get(3), "113 199.50 R");
		assertEquals(list.get(4), "114 90.00 R");
		
		
	}
	
	/**
	 * test basic example of GRP event
	 */
	@Test
	public void testNormalGRP(){
		Event e = new Event("11:14:30.0");
	}
	
	/**
	 * test basic example of PARGRP event
	 */
	@Test
	public void testNormalPARGRP(){
		Event e = new Event("11:14:30.0");
	}
}
