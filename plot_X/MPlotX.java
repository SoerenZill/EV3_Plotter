package plot_X;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

/**
 * Created by Alexander Schmidt on 2019-10-22.
 */
public class MPlotX {

	// Direction pattern
	public enum Direction {
		forward, backward
	}

	// Motor: Plotter axis X (Conveyor)
	RegulatedMotor mPlotX = new EV3LargeRegulatedMotor(MotorPort.A);;

	public MPlotX() {
	}

	/**
	 * Pulls the paper out of the Plotter
	 */
	public void throwOutPaper() {
		mPlotX.setSpeed(400);
		mPlotX.rotate(-2500);
	}

	public void move(Direction dir, int mm, int ms) {

		// TODO Add calculation for proper movement

		mPlotX.setSpeed(200);

		if (dir == Direction.forward) {
			mPlotX.rotate(mm);
		} else if (dir == Direction.backward) {
			mPlotX.rotate((mm * -1));
		}
	}

	public int getLocation() {
		mPlotX.resetTachoCount();
		return mPlotX.getTachoCount();
	}

	public void MoveXDegreeInYDegreePerSecond(int degree, int degreePerSecond) {
		LCD.drawString(mPlotX.getMaxSpeed() + "", 0, 4);
		Delay.msDelay(5000);
		mPlotX.setSpeed(degreePerSecond);
		mPlotX.rotate(degree);
	}

}
