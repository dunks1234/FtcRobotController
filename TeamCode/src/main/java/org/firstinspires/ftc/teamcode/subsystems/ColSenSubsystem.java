package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ColSenSubsystem{

    NormalizedColorSensor colorSensor;

    public ColSenSubsystem() {

    }

    public enum DetectedColor {
        GREEN,
        PURPLE,
        UNKOWN
    }
    public void init(HardwareMap hwMap) {
        colorSensor = hwMap.get(NormalizedColorSensor.class,"ColSen");
        colorSensor.setGain(5);
    }

    public DetectedColor getDetectColor (Telemetry telemetry){
        NormalizedRGBA colors = colorSensor.getNormalizedColors(); // returns 4 values

        float normRed, normGreen, normBlue;
        normRed = colors.red / colors.alpha;
        normGreen = colors.green / colors.alpha;
        normBlue = colors.blue / colors.alpha;

        /*
        Purple:
        red =
        blue =
        green =

        Green:
        red =
        blue =
        green =
         */

        telemetry.addData("red", normRed);
        telemetry.addData("green", normGreen);
        telemetry.addData("blue", normBlue);

        return DetectedColor.UNKOWN;
    }

}
