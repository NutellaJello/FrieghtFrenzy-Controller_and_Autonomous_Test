package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Arrays;

@TeleOp (name = "ControllerTest")
public class ControllerTest extends OpMode {

    //Identifying Motors and Servos
    DcMotor FL, FR, BL, BR, Arm, Intake;
    Servo LH;

    double drive;
    double strafe;
    double rotate;

    double FLPower;
    double FRPower;
    double BLPower;
    double BRPower;

    double ArmPower =0.6;

    @Override
    public void init() {
        //Connect Motor
        FL = hardwareMap.get(DcMotor.class, "FL");
        FR = hardwareMap.get(DcMotor.class, "FR");
        BL = hardwareMap.get(DcMotor.class, "BL");
        BR = hardwareMap.get(DcMotor.class, "BR");

        Arm = hardwareMap.dcMotor.get("Arm");

       // Intake = hardwareMap.dcMotor.get("Intake");

        //Connect Servo
        LH = (Servo) hardwareMap.get(Servo.class, "LH");
        //Set ZERO POWER BEHAVIOR
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        //Set Up Motor Direction
        FR.setDirection(DcMotor.Direction.FORWARD);
        FL.setDirection(DcMotor.Direction.REVERSE);
        BR.setDirection(DcMotor.Direction.FORWARD);
        BL.setDirection(DcMotor.Direction.REVERSE);

        Arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        telemetry.addData("STATUS", "Initialized");
    }

    @Override
    public void loop() {

        drive = -gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        FLPower = (drive + strafe + rotate);
        FRPower = (drive - strafe - rotate);
        BLPower = (drive - strafe + rotate);
        BRPower = (drive + strafe - rotate);

        FL.setPower(FLPower);
        FR.setPower(FRPower);
        BL.setPower(BLPower);
        BR.setPower(BRPower);

//        if (gamepad1.y) {
//            // move to 0 degrees.
//            LH.setPosition(0);
//        } else if (gamepad1.x) {
//            // move to 90 degrees.
//            LH.setPosition(0.2);
//        } else if (gamepad1.b) {
            //move to 90 degrees opposite direction
  //          LH.setPosition(-0.8);
      //  }else if (gamepad1.a) {
            // move to 180 degrees.
            //LH.setPosition(1);
     //   }
        if(gamepad1.dpad_up) {
            Arm.setPower(-ArmPower);
            Arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        else {
            Arm.setPower(0);
        }
        if (gamepad1.dpad_down) {
            Arm.setPower(ArmPower);
            Arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        else {
            Arm.setPower(0);
        }

//        Intake.setPower(gamepad1.left_trigger);
//        Intake.setPower(-gamepad1.right_trigger);

        if (gamepad1.a) {
            Arm.setTargetPosition(0);
            Arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Arm.setPower(0.7);
        }

            if (gamepad1.x) {
                Arm.setTargetPosition(-69);
                Arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Arm.setPower(0.7);
            }
            if (gamepad1.y) {
                Arm.setTargetPosition(-200);
                Arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Arm.setPower(0.7);
            }
            if (gamepad1.b) {
                Arm.setTargetPosition(-300);
                Arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Arm.setPower(0.7);
            }



        //Adding Telemetry
        //telemetry.addData("Servo Position", LH.getPosition());
        telemetry.addData("Motor Power", FL.getPower());
        telemetry.addData("Motor Power", FR.getPower());
        telemetry.addData("Motor Power", BL.getPower());
        telemetry.addData("Motor Power", BR.getPower());
        telemetry.addData("Motor Power", Arm.getPower());
        telemetry.addData("FL Power", FLPower);
        telemetry.addData("BR Power", BRPower);
        telemetry.addData("BL Power", BLPower);
        telemetry.addData("FR Power", FRPower);
        telemetry.addData("Arm Power", ArmPower);
        telemetry.addData("Status", "Running");
        telemetry.addData("Status", Arm.getPower());
        telemetry.addData("Arm", Arm.getCurrentPosition());
        telemetry.update();

    }
}























//        for (DcMotor dcMotor : Arrays.asList(FL, FR, BL, BR)) {
//            dcMotor.setPower(gamepad1.left_trigger);
//        }






