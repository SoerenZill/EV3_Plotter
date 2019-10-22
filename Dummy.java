import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import plot_Y.MPlotY;
/*
class MConyeyerX {

	RegulatedMotor mConveyerX = new EV3LargeRegulatedMotor(MotorPort.A); // motor conveyer line x angle

	void move_init() {
		mConveyerX.setSpeed(400);
		mConveyerX.rotate(-2500);
	}

	void move(int mm, int ms) {

	}

	int getLocation() {
		int tachoCount = mConveyerX.getTachoCount();
		mConveyerX.resetTachoCount();
		tachoCount = mConveyerX.getTachoCount();
		mConveyerX.close();
		return tachoCount;
	}

	void MoveXDegreeInYDegreePerSecond(int degree, int degreePerSecond, Port port) {
		LCD.drawString(mConveyerX.getMaxSpeed() + "", 0, 4);
		Delay.msDelay(5000);
		mConveyerX.setSpeed(degreePerSecond);
		mConveyerX.rotate(degree);
		mConveyerX.close();
	}

}
*/

class ColorSensor {

	EV3ColorSensor color_sensor = new EV3ColorSensor(SensorPort.S1);
	SensorMode ambientSensorMode = color_sensor.getRGBMode(); // RGB code

	float[] sample = new float[ambientSensorMode.sampleSize()]; // create array of size of return value sensor

	public ColorSensor() {
		color_sensor.setFloodlight(false);
		LCD.drawString("Init Col Sensor", 2, 2);
		LCD.setAutoRefresh(false);

	}

	float[] getRGBCode() {
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
		color_sensor.close();
		return sample;
	}

}

public class Dummy {
	/*
	 * static void printLCD(String msg) { LCD.drawString(msg, 1, 1); }
	 */

	public static void main(String[] args) {
		LCD.drawString("Init", 1, 1);
		ColorSensor ColSensor = new ColorSensor();
		float[] rgb = ColSensor.getRGBCode();
		// LCD.drawString("RGB: " + x[0] + ", " + x[1] + ", " + x[2], 1, 1);
		MPlotY PenY = new MPlotY();

		// TODO Auto-generated method stub
	}
	// TODO Auto-generated method stub

}
