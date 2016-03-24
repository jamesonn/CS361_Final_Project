import static org.junit.Assert.*;
import org.junit.Test;

public class Testing {

	Racer racer;
	Sensor sensor;
	
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
		racer.start((float) 50.0);
		racer.stop((float) 300.00);
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
	
	
}
