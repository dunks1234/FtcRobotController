package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ColSenSubsystem extends SubsystemBase {
    private final ColorSensor colorSensor;

    // Thresholds you can tune later based on your sensor readings
    private final double greenLimit = 90;   // adjust experimentally
    private final double purpleLimit = 10;  // adjust experimentally

    public ColSenSubsystem(HardwareMap hardwareMap) {
        colorSensor = hardwareMap.get(ColorSensor.class, "ColSen");
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

        // --- Detect GREEN ---
        // Green is dominant and above a certain brightness
        if (green > greenLimit && green > red * 1.3 && green > blue * 1.3) {
            return "GREEN";
        }

        // --- Detect PURPLE ---
        // Purple is a mix of RED + BLUE, both strong and green is weaker
        if (red > purpleLimit && blue > purpleLimit && green < (red + blue) * 0.6) {
            return "PURPLE";
        }

        return "UNKNOWN";
    }
}
