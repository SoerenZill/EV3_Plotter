package plot_X;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;

public class SPlotX {

	EV3ColorSensor color_sensor = new EV3ColorSensor(SensorPort.S1);
	SensorMode ambientSensorMode = color_sensor.getRGBMode(); // RGB code

	float[] sample = new float[ambientSensorMode.sampleSize()]; // create array of size of return value sensor

	public SPlotX() {
		color_sensor.setFloodlight(false);
		LCD.drawString("Init Col Sensor", 2, 2);
		LCD.setAutoRefresh(false);

	}

	public float[] getRGBCode() {
		ambientSensorMode.fetchSample(sample, 0);
		LCD.refresh();
		LCD.clear();
		LCD.drawString("RGB: " + sample[0], 1, 1);
		LCD.drawString("     " + sample[1], 1, 2);
		LCD.drawString("     " + sample[2], 1, 3);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sample;
	}

}
