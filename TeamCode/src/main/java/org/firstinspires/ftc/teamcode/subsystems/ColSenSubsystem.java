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
        colorSensor.setGain(5); // adjust tn
    }

    public DetectedColor getDetectColor (Telemetry telemetry){
        NormalizedRGBA colors = colorSensor.getNormalizedColors(); // returns 4 values

        float normRed, normGreen, normBlue;
        normRed = colors.red / colors.alpha;
        normGreen = colors.green / colors.alpha;
        normBlue = colors.blue / colors.alpha;

        /*
        Purple:
        red = 0.15
        blue = 0.16
        green = 0.21

        Green:
        red = 0.14
        blue = 0.15
        green = 0.22
         */

        telemetry.addData("red", normRed);
        telemetry.addData("green", normGreen);
        telemetry.addData("blue", normBlue);

        return DetectedColor.UNKOWN;
    }

}

