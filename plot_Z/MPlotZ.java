package plot_Z;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

/**
 * Created by Alexander Schmidt on 2019-10-22.
 *
 * Class for motor PlotZ (Pen up/down). The motor needs to be connected to Port B!
 */
public class MPlotZ {

    int positionPenDown = 180; //TODO change to proper value
    int positionPenUp = 0; //TODO change to proper value

    //Motor: Plotter axis Z (Pen)
    RegulatedMotor mPlotZ = new EV3LargeRegulatedMotor(MotorPort.B);;

    /**
     * Constructor
     *
     * moves Pen to upper position
     */
    public MPlotZ() {
        penUp();
    }

    /**
     * moves pen to up position
     */
    public void penUp(){
        mPlotZ.setSpeed(200);

        int diff = positionPenUp - mPlotZ.getTachoCount();

        mPlotZ.rotate(diff);
    }

    /**
     * moves pen to up position
     */
    public void penDown(){
        mPlotZ.setSpeed(200);

        int diff = positionPenDown - mPlotZ.getTachoCount();

        mPlotZ.rotate(diff);
    }
}
