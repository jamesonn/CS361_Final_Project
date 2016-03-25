import static org.junit.Assert.*;
import org.junit.Test;

public class Testing {

	Racer racer, racer2, racer3;
	Sensor sensor;
	Lane lane;
	
	/**
	 * test racer constructor and getters
	 */
	@Test
	public void testNewRacer(){
		racer = new Racer(001, 1);
		assertEquals(racer.getBibNum(), 001);
		assertEquals(racer.getLaneNum(), 1);
		assertEquals(racer.getStartTime(), 0.0, 0.0);
		assertEquals(racer.getEndTime(), 0.0, 0.0);
		assertEquals(racer.getTotalTime(),0.0,0.0);
	}
	
	/**
	 * test racer setters and getters
	 */
	@Test
	public void testRacerStartFinish(){
		racer = new Racer(002, 1);
		racer.start(50.0);
		racer.stop(300.00);
		assertEquals(racer.getBibNum(), 002);
		assertEquals(racer.getLaneNum(), 1);
		assertEquals(racer.getStartTime(), 50.0, 0.0);
		assertEquals(racer.getEndTime(), 300.0, 0.0);
		assertEquals(racer.getTotalTime(), 250.0, 0.0);
	}
	
	/**
	 * test start sensor constructor and getters
	 */
	
	@Test
	public void testNewStartSensor(){
		sensor = new Sensor("EYE", 1);
		assertEquals(sensor.getDeviceType(), "EYE");
		assertEquals(sensor.getSensorNumber(), 1);
		assertTrue(sensor.isStartSensor());
		assertFalse(sensor.canTriggerSensor());
	}
	
	/**
	 * test stop sensor constructor and getters
	 */
	@Test
	public void testNewStopSensor(){
		sensor = new Sensor("GATE", 2);
		assertEquals(sensor.getDeviceType(), "GATE");
		assertEquals(sensor.getSensorNumber(), 2);
		assertFalse(sensor.isStartSensor());
		assertFalse(sensor.canTriggerSensor());
	}
	
	/**
	 * test sensor toggle()
	 */
	@Test
	public void testToggleSensor(){
		sensor = new Sensor("PAD", 3);
		assertEquals(sensor.getDeviceType(), "PAD");
		assertEquals(sensor.getSensorNumber(), 3);
		assertTrue(sensor.isStartSensor());
		assertFalse(sensor.canTriggerSensor());
		sensor.toggle();
		assertTrue(sensor.canTriggerSensor());
	}
	
	@Test
	public void testLane(){
		lane = new Lane();
		racer = new Racer(001, 1);
		racer2 = new Racer(002, 1);
		racer3 = new Racer(003, 1);
		assertTrue(lane.isReadyEmpty());
		assertTrue(lane.isActiveEmpty());
		lane.addRacer(racer);
		lane.addRacer(racer2);
		lane.addRacer(racer3);
		assertFalse(lane.isReadyEmpty());
		assertTrue(lane.isActiveEmpty());
		lane.start(100.00);	//racer start
		lane.start(200.00);	//racer2 start
		lane.start(300.00);	//racer3 start
		assertTrue(lane.isReadyEmpty());
		assertFalse(lane.isActiveEmpty());
		lane.stop(400.00);	//racer stop
		assertEquals(racer.getTotalTime(), 300.00, 0.0);
		lane.swap(); //swap racer2 and racer3
		lane.stop(500.00); //racer3 stop
		assertEquals(racer3.getTotalTime(), 200.00, 0.0);
		lane.stop(600.00);	//racer2 stop
		assertEquals(racer2.getTotalTime(), 400.00, 0.0);
		assertTrue(lane.isActiveEmpty());
	}
	
	
}
