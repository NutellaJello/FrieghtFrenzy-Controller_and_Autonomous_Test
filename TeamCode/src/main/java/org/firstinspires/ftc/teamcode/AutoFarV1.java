package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


@Autonomous (name= "AutoFarV1")
public class AutoFarV1 extends LinearOpMode {

    SampleMecanumDrive drive;


    @Override
    public void runOpMode() throws InterruptedException {
        drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(15, 56, Math.toRadians(90));
        drive.setPoseEstimate(startPose);
        TrajectorySequence traj = drive.trajectorySequenceBuilder(new Pose2d(15, 56, Math.toRadians(90)))
                .lineToConstantHeading(new Vector2d(-11, 35))
                .lineToConstantHeading(new Vector2d(-59, 31))
                .lineToConstantHeading(new Vector2d(-59, 50))
                .lineToConstantHeading(new Vector2d(-59, 36))
                .lineToSplineHeading(new Pose2d(-8, 31, Math.toRadians(0)))
                .lineToConstantHeading(new Vector2d(50, 36))
                .build();
        waitForStart();
        drive.followTrajectorySequence(traj);
        while(!isStopRequested() && drive.isBusy());
    }
}



