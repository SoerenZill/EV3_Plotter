import lejos.hardware.lcd.LCD;
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

		while (true) {
			float[] rgb = ColSensor.getRGBCode();
			if (rgb[0] >= 0.1 && rgb[1] >= 0.1 && rgb[2] >= 0.1) {
				break;
			}
		}

		MPlotX ConvX = new MPlotX();

		ConvX.move(Direction.forward, 360, 0);
		MPlotY PenY = new MPlotY();
		// TODO Auto-generated method stub
	}
	// TODO Auto-generated method stub

}
