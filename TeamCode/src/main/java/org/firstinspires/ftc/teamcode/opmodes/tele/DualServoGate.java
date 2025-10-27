package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
@Config
@TeleOp(name = "DualServoGate")
public class DualServoGate extends LinearOpMode {

    // Declare two servos
    private Servo servo1;
    private Servo servo2
    // Define position constants
    private final double POS_OPEN = 0.0;   // Corresponds to -90°
    private final double POS_CLOSED = 0.5; // Corresponds to 0°
    private final double POS_EXTRA = 1.0;  // Corresponds to +90°

    // Keep track of current position state
    private double currentPosition = POS_OPEN;

    @Override
    public void runOpMode() {
        servo1 = hardwareMap.get(Servo.class, "servo1");
        servo2 = hardwareMap.get(Servo.class, "servo2");

        servo1.setPosition(POS_OPEN);
        servo2.setPosition(POS_OPEN);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                currentPosition = POS_CLOSED;
                servo1.setPosition(currentPosition);
                servo2.setPosition(currentPosition);
            }

            if (gamepad1.b) {
                currentPosition = POS_OPEN;
                servo1.setPosition(currentPosition);
                servo2.setPosition(currentPosition);
            }

            if (gamepad1.y) {
                currentPosition = POS_EXTRA;
                servo1.setPosition(currentPosition);
                servo2.setPosition(currentPosition);
            }
            telemetry.addData("Servo Position", currentPosition);
            telemetry.update();
        }
    }
}

