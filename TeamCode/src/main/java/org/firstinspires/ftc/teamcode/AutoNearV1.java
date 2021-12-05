package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name= "AutoNearV1")
public class AutoNearV1 extends LinearOpMode {

    SampleMecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(-36, 60, Math.toRadians(90));
        drive.setPoseEstimate(startPose);
        TrajectorySequence traj = drive.trajectorySequenceBuilder(new Pose2d(-36, 60, Math.toRadians(90)))
                .lineToConstantHeading(new Vector2d(-12, 41))
                .lineToConstantHeading(new Vector2d(-57, 55))
                .lineToSplineHeading(new Pose2d(-61, 33, Math.toRadians(0)))
                .build();

        waitForStart();
        drive.followTrajectorySequence(traj);
        while (!isStopRequested() && drive.isBusy()) ;
    }
}
