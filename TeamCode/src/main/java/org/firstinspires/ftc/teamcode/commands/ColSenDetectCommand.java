package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ColSenSubsystem;
public class ColSenDetectCommand extends CommandBase {

    private final ColSenSubsystem colSenSubsystem;

    public ColSenDetectCommand(ColSenSubsystem colSenSubsystem) {
        this.colSenSubsystem = colSenSubsystem;
    }

    public void execute() {
        colSenSubsystem.detectColor();
    }
}
