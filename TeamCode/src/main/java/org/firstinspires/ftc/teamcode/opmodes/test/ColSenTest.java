package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;

import org.firstinspires.ftc.teamcode.subsystems.ColSenSubsystem;
@TeleOp
public class ColSenTest extends OpMode {
    ColSenSubsystem colsen = new ColSenSubsystem();

    @Override
    public void init() {
        colsen.init(hardwareMap);

    }

    @Override
    public void loop() {
        colsen.getDetectColor(telemetry);

    }
}
