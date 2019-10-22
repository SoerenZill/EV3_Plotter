package plot_Y;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;

/**
 * Created by Alexander Schmidt on 2019-10-08.
 */
class SPlotY {

    //Sensor: Plotter axis Y, touch sensor, detecting basic position
    private EV3TouchSensor sPlotY;

    //MPlotY Object
    private MPlotY mPlotY;
    MPlotY getMPlotY() {
        return mPlotY;
    } //Getter

    //Run-condition for thread: checkSensor
    private boolean checkSPlotY = true;


    /**
     * Constructor: SPlotY
     * @param mPlotY
     */
    public SPlotY(MPlotY mPlotY) {

        this.mPlotY = mPlotY;

        //initialise sPlotY
        sPlotY = new EV3TouchSensor(SensorPort.S4);
        //Set SensorMode for sPlotY
        SensorMode sensorMode = sPlotY.getTouchMode();

        //Create an array to store sensor data
        float[] sPlotYResult = new float[sensorMode.sampleSize()];


        /**
         * (background)
         * Thread checking if sPlotY is touched.
         * If touched mPlotY stops.
         */
        Runnable checkSensor = () -> {
            while (checkSPlotY) {

                //Get sensor value; value will be stored in sPlotResult[0]
                sensorMode.fetchSample(sPlotYResult, 0);

                //Check if sensor is touched
                if (sPlotYResult[0] == 5.5 /*TODO change to proper value*/ ) {
                    //Stop movement
                    getMPlotY().stopMPlotY();
                }

                try {
                    Thread.sleep(10);
                }catch (InterruptedException ignored) { }
            }
        };
        //Start checkSensor-Thread
        new Thread(checkSensor).start();
    }



}
