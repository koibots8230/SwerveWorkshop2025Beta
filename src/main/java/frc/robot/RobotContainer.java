// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Swerve;
import java.util.Optional;

@Logged
public class RobotContainer {

  private final XboxController driverController;
  private final Swerve swerve;

  public RobotContainer() {
    driverController = new XboxController(0);
    swerve = new Swerve();

    configureBindings();
  }

  private void configureBindings() {
    Trigger headingResetTrigger =
        new JoystickButton(
            driverController,
            XboxController.Button.kA
                .value); // Creates a new JoystickButton object for the `Y` button on
    // exampleController
    headingResetTrigger.onTrue(
        new InstantCommand(
                () ->
                    swerve.resetHeading(
                        Alliance.Blue == DriverStation.getAlliance().get() ? 0.0 : 180.0),
                swerve)
            .ignoringDisable(true));
  }

  public void teleopInit() {
    Optional<Alliance> allianceOpt = DriverStation.getAlliance();
    Alliance alliance = allianceOpt.get();
    System.out.println("Setting alliance color to: " + alliance);
    swerve.setDefaultCommand(
        Alliance.Blue == alliance
            ? new RunCommand(
                () ->
                    swerve.drive(
                        -MathUtil.applyDeadband(driverController.getLeftY(), 0.05),
                        -MathUtil.applyDeadband(driverController.getLeftX(), 0.05),
                        -MathUtil.applyDeadband(driverController.getRightX(), 0.05)),
                swerve)
            : new RunCommand(
                () ->
                    swerve.drive(
                        MathUtil.applyDeadband(driverController.getLeftY(), 0.05),
                        MathUtil.applyDeadband(driverController.getLeftX(), 0.05),
                        -MathUtil.applyDeadband(driverController.getRightX(), 0.05)),
                swerve));
  }

  public void disabledPeriodic() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
