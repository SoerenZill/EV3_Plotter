package plot_Z;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

/**
 * Created by Alexander Schmidt on 2019-10-22.
 */
public class MPlotZ {

    int positionPenDown = 0; //TODO change to proper value
    int positionPenUp = 0; //TODO change to proper value

    //Motor: Plotter axis Z (Pen)
    RegulatedMotor mPlotZ = new EV3LargeRegulatedMotor(MotorPort.B);;

    public MPlotZ() {

    }

    public void penUp(){
        mPlotZ.setSpeed(200);

        int diff = positionPenUp - mPlotZ.getTachoCount();

        mPlotZ.rotate(diff);
    }
}
