package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "FieldCentricOdometryDrive")
public class FieldCentricOdometryDrive extends LinearOpMode {

    // --- Motor declarations ---
    DcMotor rfmotor, lfmotor, rbmotor, lbmotor;

    // --- Odometry encoders (use 3 tracking wheels) ---
    DcMotor par0, par1, perp;

    // --- Constants for your robot ---
    final double TICKS_PER_REV = 2000;      // REV through-bore encoder example
    final double WHEEL_DIAMETER = 1.25;      // in inches
    final double TRACK_WIDTH = 9.25;        // distance (in inches) between left & right pods
    final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;

    // --- Variables to track heading and position ---
    double heading = 0.0;
    double prevLeftPos = 0.0;
    double prevRightPos = 0.0;

    @Override
    public void runOpMode() throws InterruptedException {

        // --- Map hardware ---
        rfmotor = hardwareMap.get(DcMotor.class, "rf");
        lfmotor = hardwareMap.get(DcMotor.class, "lf");
        rbmotor = hardwareMap.get(DcMotor.class, "rb");
        lbmotor = hardwareMap.get(DcMotor.class, "lb");

        par0 = hardwareMap.get(DcMotor.class, "rf");
        par1 = hardwareMap.get(DcMotor.class, "rb");
        perp = hardwareMap.get(DcMotor.class, "lf");

        // motor directions
        rfmotor.setDirection(DcMotor.Direction.REVERSE);
        lfmotor.setDirection(DcMotor.Direction.FORWARD);
        rbmotor.setDirection(DcMotor.Direction.REVERSE);
        lbmotor.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();

        while (opModeIsActive()) {

            // --- 1️⃣ Read current odometry pod positions ---
            double leftTicks = par0.getCurrentPosition();
            double rightTicks = par1.getCurrentPosition();

            // --- 2️⃣ Convert encoder ticks to distance (in inches) ---
            double leftInches = (leftTicks / TICKS_PER_REV) * WHEEL_CIRCUMFERENCE;
            double rightInches = (rightTicks / TICKS_PER_REV) * WHEEL_CIRCUMFERENCE;

            // --- 3️⃣ Calculate change since last loop ---
            double deltaLeft = leftInches - prevLeftPos;
            double deltaRight = rightInches - prevRightPos;

            // --- 4️⃣ Estimate new heading ---
            // Difference between left and right movements gives rotation.
            double deltaTheta = (deltaRight - deltaLeft) / TRACK_WIDTH;
            heading += deltaTheta; // radians

            // Store current values for next loop
            prevLeftPos = leftInches;
            prevRightPos = rightInches;

            // --- 5️⃣ Get driver joystick inputs ---
            double y = -gamepad1.left_stick_y;   // forward/back
            double x = gamepad1.left_stick_x;    // strafe
            double rx = gamepad1.right_stick_x;  // rotation

            // --- 6️⃣ Rotate the joystick vector by negative heading (field-centric) ---
            double rotatedX = x * Math.cos(-heading) - y * Math.sin(-heading);
            double rotatedY = x * Math.sin(-heading) + y * Math.cos(-heading);

            // --- 7️⃣ Apply mecanum drive math ---
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            lfmotor.setPower(frontLeftPower);
            lbmotor.setPower(backLeftPower);
            rfmotor.setPower(frontRightPower);
            rbmotor.setPower(backRightPower);

            // --- 9️⃣ Telemetry for debugging ---
            telemetry.addData("Heading (deg)", Math.toDegrees(heading));
            telemetry.addData("Left (in)", leftInches);
            telemetry.addData("Right (in)", rightInches);
            telemetry.update();
        }
    }
}
