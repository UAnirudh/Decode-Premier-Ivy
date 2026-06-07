package org.firstinspires.ftc.teamcode.commands.subsystem;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.behaviors.BlockedBehavior;
import com.pedropathing.ivy.behaviors.ConflictBehavior;
import com.pedropathing.ivy.behaviors.EndCondition;
import com.pedropathing.ivy.behaviors.InterruptedBehavior;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.hardware.subsystems.IntakeSubsystem;

import java.util.Collections;
import java.util.Set;

public class IntakeStateCommand implements Command {

    private final IntakeSubsystem.IntakeState state;
    private boolean finished = false;

    public IntakeStateCommand(IntakeSubsystem.IntakeState state) {
        this.state = state;
    }

    @Override
    public void start() {
        Robot.getInstance().intakeSubsystem.updateIntakeState(state);
        finished = true;
    }

    @Override
    public boolean done() {
        return finished;
    }

    @Override
    public void execute() {     }

    @Override
    public Set<Object> requirements() {
        return Collections.emptySet();
    }

    @Override
    public int priority() { return 1; }

    @Override
    public InterruptedBehavior interruptedBehavior() { return InterruptedBehavior.END; }

    @Override
    public ConflictBehavior conflictBehavior() { return ConflictBehavior.QUEUE; }

    @Override
    public BlockedBehavior blockedBehavior() { return BlockedBehavior.CANCEL; }

    @Override
    public void end(EndCondition endCondition) { }
}
