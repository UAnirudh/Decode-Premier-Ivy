package org.firstinspires.ftc.teamcode.commands.drivecommand;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.behaviors.BlockedBehavior;
import com.pedropathing.ivy.behaviors.ConflictBehavior;
import com.pedropathing.ivy.behaviors.EndCondition;
import com.pedropathing.ivy.behaviors.InterruptedBehavior;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.hardware.Robot;

import java.util.Collections;
import java.util.Set;

public class PathCommand implements Command {

    private final PathChain path;
    private final Robot robot = Robot.getInstance();
    private final double speed;

    public PathCommand(Path path) {
        this.path = new PathChain(path);
        this.speed = 1;
    }

    public PathCommand(Path path, double speed) {
        this.path = new PathChain(path);
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
        robot.follower.setMaxPower(speed);
        robot.follower.followPath(path, true);
    }

    @Override
    public void execute() {
        robot.follower.update();
    }

    @Override
    public boolean done() {
        return !robot.follower.isBusy();
    }

    @Override
    public void end(EndCondition endCondition) {
        robot.follower.setMaxPower(1);
    }
}
