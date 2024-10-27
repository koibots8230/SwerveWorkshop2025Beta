// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.Swerve;
import java.util.Optional;

public class RobotContainer {

  private final XboxController driverController;
  private final Swerve swerve;

  private Alliance alliance = null;

  public RobotContainer() {
    driverController = new XboxController(0);
    swerve = new Swerve();

    configureBindings();
  }

  private void configureBindings() {}

  public void teleopInit() {
    swerve.setDefaultCommand(
        Alliance.Blue == alliance
            ? new RunCommand(
                () ->
                    swerve.driveFieldOriented(
                        new ChassisSpeeds(
                            -driverController.getLeftY(),
                            -driverController.getLeftX(),
                            -driverController.getRightX())),
                swerve)
            : new RunCommand(
                () ->
                    swerve.driveFieldOriented(
                        new ChassisSpeeds(
                            driverController.getLeftY(),
                            driverController.getLeftX(),
                            -driverController.getRightX())),
                swerve));
  }

  public void disabledPeriodic() {
    Optional<Alliance> ally = DriverStation.getAlliance();
    if (ally.isPresent()) {
      alliance = ally.get();
    }
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
