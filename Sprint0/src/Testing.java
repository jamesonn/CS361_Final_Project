import static org.junit.Assert.*;

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
		racer = new Racer(111, 1);
		assertEquals(racer.getBibNum(), 111);
		assertEquals(racer.getLaneNum(), 1);
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
}
