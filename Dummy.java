import lejos.hardware.lcd.LCD;
import plot_X.MPlotX;
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
		MPlotX ConvX = new MPlotX();
		MPlotY PenY = new MPlotY();

		// EV3TouchSensor testTouch = new EV3TouchSensor(SensorPort.S2);
		// SensorMode sensorMode = testTouch.getTouchMode();
		// float[] testTouchResult = new float[sensorMode.sampleSize()];

		while (true) {
			float[] rgb = ColSensor.getRGBCode();
			if (rgb[0] >= 0.1 && rgb[1] >= 0.1 && rgb[2] >= 0.1) {
				ConvX.move(MPlotX.Direction.forward, 70, 2000);
				break;
			}
		}

		while (true) {
			// sensorMode.fetchSample(testTouchResult, 0);
			// if (testTouchResult[0] == 0.5)
			if (true) {
				// PenY.move(MPlotY.Direction.backward, 50, 0);
				ConvX.move(MPlotX.Direction.forward, 70, 1000);
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
