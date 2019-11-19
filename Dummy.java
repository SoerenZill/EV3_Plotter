import lejos.hardware.lcd.LCD;
import lejos.robotics.RegulatedMotor;
import plot_X.MPlotX;
import plot_X.SPlotX;
import plot_Y.MPlotY;
import plot_Z.MPlotZ;

public class Dummy {

	static SPlotX ColSensor = new SPlotX();
	static MPlotX ConvX = new MPlotX();
	static MPlotY PenY = new MPlotY();
	static MPlotZ PenZ = new MPlotZ();

	static void printLCD(String msg) {
		LCD.clear();
		LCD.drawString(msg, 1, 1);
	}

	public static void main(String[] args) {
		LCD.drawString("Init", 1, 1);

		// EV3TouchSensor testTouch = new EV3TouchSensor(SensorPort.S2);
		// SensorMode sensorMode = testTouch.getTouchMode();
		// float[] testTouchResult = new float[sensorMode.sampleSize()];

		// sensorMode.fetchSample(testTouchResult, 0);
		// if (testTouchResult[0] == 0.5)

		while (true) {
			int r = 10;
			int y = 10;

			int posX = 0, posY = 0;

			PenZ.penUp();

			float[] rgb = ColSensor.getRGBCode();
			if (rgb[0] >= 0.1 && rgb[1] >= 0.1 && rgb[2] >= 0.1) {

				PenZ.penUp();

				// insert paper
				ConvX.move(MPlotX.Direction.forward, 70, 3000); // mm in ms

				PenZ.penDown();

				synchro(r, 0, 5000);

				for (int x = 0; x < r; x++) {
					y = (int) (Math.sqrt((Math.pow(r, 2) - Math.pow(x, 2))));
					printLCD(Integer.toString(y));

					synchro(x - posX, y - posY, 2000);

					posX = x;
					posY = y;

				}

				// synchro(x, y, 5000);

				// synchro(70, 80, 5000);
				// synchro(-70, 0, 5000);
				// synchro(0, -80, 5000);

				PenZ.penUp();

				try {
					Thread.sleep(10);
				} catch (Exception e) {
					System.err.println(e);
				}
				ConvX.throwOutPaper();
			}

		}

		// TODO Auto-generated method stub
	}
	// TODO Auto-generated method stub

	private static void synchro(int x_mm, int y_mm, int ms) {

		// double hypotenuse = Math.sqrt( (Math.pow(x_mm, 2) + Math.pow(x_mm, 2)) );

		ConvX.mPlotX.synchronizeWith(new RegulatedMotor[] { PenY.mPlotY });
		ConvX.mPlotX.startSynchronization();

		PenY.move(MPlotY.Direction.backward, y_mm, ms);
		ConvX.move(MPlotX.Direction.forward, x_mm, ms);

		ConvX.mPlotX.endSynchronization();
		ConvX.mPlotX.waitComplete();
		PenY.mPlotY.waitComplete();
	}

}
