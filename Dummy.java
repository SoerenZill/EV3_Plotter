import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;
import plot_X.MPlotX;
import plot_X.MPlotX.Direction;
import plot_X.SPlotX;
import plot_Y.MPlotY;

public class Dummy {

	static void printLCD(String msg) {
		LCD.clear();
		LCD.drawString(msg, 1, 1);
	}

	public static void main(String[] args) {
		LCD.drawString("Init", 1, 1);
		SPlotX ColSensor = new SPlotX();

		EV3TouchSensor testTouch = new EV3TouchSensor(SensorPort.S2);
		SensorMode sensorMode = testTouch.getTouchMode();
		float[] testTouchResult = new float[sensorMode.sampleSize()];

		while (true) {
			float[] rgb = ColSensor.getRGBCode();
			if (rgb[0] >= 0.1 && rgb[1] >= 0.1 && rgb[2] >= 0.1) {
				break;
			}
		}

		MPlotX ConvX = new MPlotX();

		MPlotY PenY = new MPlotY();

		while (true) {
			sensorMode.fetchSample(testTouchResult, 0);

			if (testTouchResult[0] > 0.5) {
				ConvX.move(Direction.forward, 1080, 0);
				try {
					Thread.sleep(10);
				} catch (Exception e) {
					System.err.println(e);
				}
			}
		}

		// TODO Auto-generated method stub
	}
	// TODO Auto-generated method stub

}
