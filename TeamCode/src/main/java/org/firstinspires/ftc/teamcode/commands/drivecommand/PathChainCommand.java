package org.firstinspires.ftc.teamcode.commands.drivecommand;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.behaviors.BlockedBehavior;
import com.pedropathing.ivy.behaviors.ConflictBehavior;
import com.pedropathing.ivy.behaviors.EndCondition;
import com.pedropathing.ivy.behaviors.InterruptedBehavior;
import com.pedropathing.paths.PathChain;
import com.pedropathing.paths.Path;

import org.firstinspires.ftc.teamcode.hardware.Robot;

import java.util.Collections;
import java.util.Set;

public class PathChainCommand implements Command {
    private final PathChain pathChain;

    private final Robot robot = Robot.getInstance();

    private final double speed;

    public PathChainCommand(Path... paths) {
        this.pathChain = new PathChain(paths);
        this.speed = 1;
    }

    public PathChainCommand(double speed, Path... paths) {
        this.pathChain = new PathChain(paths);
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
        robot.follower.setMaxPower(1);
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
        robot.follower.setMaxPower(speed);
        robot.follower.followPath(pathChain, false);
    }

    @Override
    public boolean done() {
        return !robot.follower.isBusy();
    }

    @Override
    public void execute() {

    }

    @Override
    public void end(EndCondition endCondition) {
        robot.follower.setMaxPower(1);
    }
}