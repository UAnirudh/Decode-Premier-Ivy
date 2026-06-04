package org.firstinspires.ftc.teamcode.commands.drivecommand;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.behaviors.BlockedBehavior;
import com.pedropathing.ivy.behaviors.ConflictBehavior;
import com.pedropathing.ivy.behaviors.EndCondition;
import com.pedropathing.ivy.behaviors.InterruptedBehavior;
import com.pedropathing.paths.PathChain;
import com.pedropathing.paths.*;
import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.opmode.auto.AutonomousMethods;

import java.util.Collections;
import java.util.Set;
import java.util.function.DoubleSupplier;

public class StrafeCommand implements Command {
    private final double inches;

    private final Robot robot = Robot.getInstance();

    private final double speed;

    public StrafeCommand(DoubleSupplier inches) {
        this.inches = inches.getAsDouble();
        this.speed = 1;
    }

    public StrafeCommand(double inches, double speed) {
        this.inches = inches;
        this.speed = speed;
    }

    @Override
    public Set<Object> requirements() {
        return Collections.emptySet();
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public InterruptedBehavior interruptedBehavior() {
        return InterruptedBehavior.END;
    }

    @Override
    public ConflictBehavior conflictBehavior() {
        return ConflictBehavior.CANCEL;
    }

    @Override
    public BlockedBehavior blockedBehavior() {
        return BlockedBehavior.QUEUE;
    }

    @Override
    public void start() {
        Pose current = robot.follower.getPose();

        Pose target = new Pose(
                current.getX() + inches,
                current.getY(),
                current.getHeading()
        );

        PathChain path = new PathChain(AutonomousMethods.buildPath(current, target));

        robot.follower.setMaxPower(speed);
        robot.follower.followPath(path, true);
    }

    @Override
    public boolean done() {
        return !robot.follower.isBusy();
    }

    @Override
    public void execute() {
        robot.follower.update();
    }

    @Override
    public void end(EndCondition endCondition) {
        robot.follower.setMaxPower(1);
    }
}