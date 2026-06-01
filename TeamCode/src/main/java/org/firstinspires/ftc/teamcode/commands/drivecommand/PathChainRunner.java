package org.firstinspires.ftc.teamcode.commands.drivecommand;

import com.pedropathing.paths.PathChain;
import com.pedropathing.paths.Path;

import org.firstinspires.ftc.teamcode.hardware.Robot;

public class PathChainRunner {

    private final Robot robot = Robot.getInstance();

    private final PathChain pathChain;
    private final double speed;

    public PathChainRunner(Path... paths) {
        this.pathChain = new PathChain(paths);
        this.speed = 1;
    }

    public PathChainRunner(double speed, Path... paths) {
        this.pathChain = new PathChain(paths);
        this.speed = speed;
    }

    /**
     * Runs the path chain and blocks until the follower finishes.
     */
    public void run() {
        robot.follower.setMaxPower(speed);
        robot.follower.followPath(pathChain, false);

        while (robot.follower.isBusy()) {
            robot.follower.update();
        }

        robot.follower.setMaxPower(1);
    }
}
