package plot_Y;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

/**
 * Created by Alexander Schmidt on 2019-10-08.
 *
 * Class for motor PlotY (PenY). The motor needs to be connected to Port A!
 */
public class MPlotY {

    // 1200 degree / (38.2 * pi) +- correction
    final double CONVERT_VALUE = 9.564599945;

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

        mPlotY = new EV3LargeRegulatedMotor(MotorPort.A);
        sPlotY = new SPlotY(this);

        moveMPlotY(Direction.forward, 100);  //Move to ground position

    }

    /**
     * Stop movement axis Y
     */
    public void stopMPlotY() {
        mPlotY.stop();
    }


    /**
     * Moves axis Y (PenY) with given speed. Movement does NOT stop automatically!
     * Be careful using this function. There's only a limit for moving forward. Moving backward may damage the Plotter!
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

    /**
     *  Move mPlotY (PenY)
     *
     * @param dir Direction of movement, use .forward / .backward
     * @param mm length of movement in millimeter
     * @param ms not used yet TODO use it
     */
    public void move(Direction dir, int mm, int ms) {

        //Convert mm to degree
        int degree = (int) (mm * CONVERT_VALUE);

        //Calculate speed in degree per second, if ms==0 : use maxSpeed
        int speed;

        if(ms <= 0){ // avoid division by zero
            speed = (int) mPlotY.getMaxSpeed();
        }else if((degree / (ms / 1000)) > mPlotY.getMaxSpeed()){ // avoid getting over maxSpeed
            speed = (int) mPlotY.getMaxSpeed();
        }else{ // normal case
            speed = degree / (ms / 1000);
        }

        //set Speed
        mPlotY.setSpeed(speed);

        //Move mPlotY
        moveMPlotYByDegree(dir, speed, degree);
    }

    /**
     * Move mPlotY (PenY) by angle in degree with given speed.
     *
     * @param direction
     * @param speed
     * @param angle
     */
    public void moveMPlotYByDegree(Direction direction, int speed, int angle){

        //Set speed of mPlotY
        mPlotY.setSpeed(speed);

        //Apply direction
        if (direction == Direction.forward) {
            mPlotY.rotate(angle);
        }else if (direction == Direction.backward){
            mPlotY.rotate((angle * -1));
        }
    }

}
