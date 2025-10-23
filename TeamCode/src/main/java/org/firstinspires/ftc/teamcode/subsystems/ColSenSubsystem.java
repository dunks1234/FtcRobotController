package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class ColSenSubsystem extends SubsystemBase {
    private ColorSensor colorSensor;
    private double redLimit;
    private double greenLimit;
    private double blueLimit;

    public ColSenSubsystem(HardwareMap hardwareMap) {
        colorSensor = hardwareMap.get(ColorSensor.class, "_____");
    }

    public int getRed() {
        return colorSensor.red();
    }

    public int getGreen() {
        return colorSensor.green();
    }

    public int getBlue() {
        return colorSensor.blue();
    }

    public String detectColor() {
        int red = getRed();
        int green = getGreen();
        int blue = getBlue();

        if (red > redLimit && red > green && red > blue) {
            return "RED";
        } else if (blue > blueLimit && blue > red && blue > green) {
            return "BLUE";
        } else if (green > greenLimit && green > red && green > blue) {
            return "GREEN";
        } else {
            return "UNKNOWN";
        }
    }
}
