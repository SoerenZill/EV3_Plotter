package plot_Y;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;

/**
 * Created by Alexander Schmidt on 2019-10-08.
 */
class SPlotY extends Thread {

	// Sensor: Plotter axis Y, touch sensor, detecting basic position
	private EV3TouchSensor sPlotY;

	// MPlotY Object
	private MPlotY mPlotY;

	MPlotY getMPlotY() {
		return mPlotY;
	} // Getter

	// Run-condition for thread: checkSensor
	boolean checkSPlotY = true;

	// Set SensorMode for sPlotY
	SensorMode sensorMode;

	// Create an array to store sensor data
	float[] sPlotYResult;

	/**
	 * Constructor: SPlotY
	 * 
	 * @param mPlotY Motor The sensor works with
	 */
	public SPlotY(MPlotY mPlotY) {

		this.mPlotY = mPlotY;

		// initialise sPlotY
		sPlotY = new EV3TouchSensor(SensorPort.S4);
		// initialise sensorMode
		sensorMode = sPlotY.getTouchMode();

		// initialise sPlotYResult
		sPlotYResult = new float[sensorMode.sampleSize()];

		LCD.drawString("SPlotY init", 2, 2);

		// Start checkSensor-Thread
		this.start();
	}

	/**
	 * (background) Thread checking if sPlotY is touched. If touched mPlotY stops.
	 */
	@Override
	public void run() {
		LCD.drawString("Thread started", 1, 1);
		while (checkSPlotY) {

			// Get sensor value; value will be stored in sPlotResult[0]
			sensorMode.fetchSample(sPlotYResult, 0);

			// Check if sensor is touched
			if (sPlotYResult[0] > 0.5 /* value ether 0.0 or 1.0 */ ) {
				// Stop movement
				getMPlotY().stopMPlotY();
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException ignored) {
				System.out.println(ignored);
			}
		}
	}
}
