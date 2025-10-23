package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;

import org.firstinspires.ftc.teamcode.commands.ColSenDetectCommand;
import org.firstinspires.ftc.teamcode.subsystems.ColSenSubsystem;
@TeleOp
public class ColSenTest extends OpMode {
    ColSenSubsystem colSenSubsystem;
    ColSenDetectCommand colSenDetectCommand;

    @Override
    public void init() {
        colSenSubsystem = new ColSenSubsystem(hardwareMap);
        colSenDetectCommand = new ColSenDetectCommand(colSenSubsystem);
    }

    @Override
    public void loop() {
        colSenDetectCommand.execute();
        telemetry.addData(colSenSubsystem.detectColor(), "Colour");
    }
}
