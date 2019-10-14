import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

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

class print {
	void printLCD(String msg) {
		LCD.drawString(msg, 1, 1);
	}
}

public class Dummy {

	public static void main(String[] args) {
		RegulatedMotor m_conveyor = new EV3LargeRegulatedMotor(MotorPort.A);
		RegulatedMotor m_z = new EV3LargeRegulatedMotor(MotorPort.C); // pen up down motor
		RegulatedMotor m_y = new EV3LargeRegulatedMotor(MotorPort.B); // pen left right motor

		EV3ColorSensor light_sensor = new EV3ColorSensor(SensorPort.S1);
		light_sensor.setFloodlight(false);
		LCD.drawString("Init", 2, 2);
		LCD.setAutoRefresh(false);
		SensorMode ambientSensorMode = light_sensor.getAmbientMode();

		float[] sample = new float[ambientSensorMode.sampleSize()];

		while (true) {
			ambientSensorMode.fetchSample(sample, 0);
			LCD.refresh();
			LCD.clear();
			LCD.drawString("Intensity: " + sample[0], 1, 1);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (sample[0] < 0.2) {

				m_conveyor.setSpeed(400);
				m_y.setSpeed(50);
				m_z.setSpeed(150);

				// m_pen.rotate(120);
				m_conveyor.rotate(70);

			}
			if (sample[0] >= 0.33) {
				m_conveyor.rotate(-2500);
				break;
			}
		} // end true
		light_sensor.close();
		m_conveyor.close();
		m_z.close();
		m_y.close();

	}

	public Dummy() {

		// TODO Auto-generated method stub
	}
	// TODO Auto-generated method stub

}
