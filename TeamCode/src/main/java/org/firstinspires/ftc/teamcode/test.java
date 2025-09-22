package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
@Autonomous
@Disabled

public class test extends OpMode {

    @Override
    public void init() {
        telemetry.addData("hello","world");
    }

    @Override
    public void loop() {

    }
}
