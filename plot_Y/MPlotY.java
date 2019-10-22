package plot_Y;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

/**
 * Created by Alexander Schmidt on 2019-10-08.
 */
public class MPlotY {

    final double CONVERT_VALUE = 31.25;

    // Direction pattern, forward:  -> Brick, backward:  <- brick
    public enum Direction {
        forward,
        backward
    }

    //Motor: Plotter axis Y
    RegulatedMotor mPlotY;

    //Sensor: Plotter axis Y, touch sensor, detecting basic position
    SPlotY sPlotY;

    /**
     * Constructor: MPlotY
     *
     * 1. initialising mPlotY, sPlotY
     * 2. go to basic position
     */
    public MPlotY() {
        mPlotY = new EV3LargeRegulatedMotor(MotorPort.C);

        sPlotY = new SPlotY(this);

        moveMPlotY(Direction.forward, 20);  //Move to ground position

    }

    /**
     * Stop movement, axis Y
     */
    public void stopMPlotY() {
        mPlotY.stop();
    }


    /**
     *
     * @param direction direction of movement, use .forward / .backward
     * @param speed speed of movement, range 0..5 TODO change to proper value
     */
    public void moveMPlotY(Direction direction, int speed) {

        mPlotY.setSpeed(speed);

        if (direction == Direction.forward) {
            mPlotY.forward();
        }else if (direction == Direction.backward){
            mPlotY.backward();
        }
    }

    public void move(Direction dir, int mm, int ms) {

        mPlotY.setSpeed(200);

        int degree = (int) (mm * CONVERT_VALUE);

        if (dir == Direction.forward) {
            mPlotY.rotate(degree);
        } else if (dir == Direction.backward) {
            mPlotY.rotate((degree * -1));
        }
    }

    public void moveMPlotYByDegree(Direction direction, int speed, int angle){

        mPlotY.setSpeed(speed);

        if (direction == Direction.forward) {
            mPlotY.rotate(angle);
        }else if (direction == Direction.backward){
            mPlotY.rotate((angle * -1));
        }
    }

}
