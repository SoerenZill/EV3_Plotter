package plot_Y;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

/**
 * Created by Alexander Schmidt on 2019-10-08.
 */
public class MPlotY {

	// Direction pattern
	enum Direction {
		forward, backward
	}

	// Motor: Plotter axis Y
	RegulatedMotor mPlotY;

	// Sensor: Plotter axis Y, touch sensor, detecting basic position
	SPlotY sPlotY;

	/**
	 * Constructor: MPlotY
	 *
	 * 1. initialising mPlotY, sPlotY 2. go to basic position
	 */
	public MPlotY() {
		mPlotY = new EV3LargeRegulatedMotor(MotorPort.C);

		sPlotY = new SPlotY(this);

		moveMPlotY(Direction.forward, 20); // Move to ground position

	}

	/**
	 * Stop movement, axis Y
	 */
	void stopMPlotY() {
		mPlotY.stop();
	}

	/**
	 *
	 * @param direction direction of movement, use .forward / .backward
	 * @param speed     speed of movement, range 0..5 TODO change to proper value
	 */
	void moveMPlotY(Direction direction, int speed) {

		mPlotY.setSpeed(speed);

		if (direction == Direction.forward) {
			mPlotY.forward();
		} else if (direction == Direction.backward) {
			mPlotY.backward();
		}
	}

}
