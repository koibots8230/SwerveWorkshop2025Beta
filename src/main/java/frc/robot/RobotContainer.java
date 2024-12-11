// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Swerve;

// STEP 1: Add the epilogue @Logged annotation
@Logged
public class RobotContainer {

  // STEP 2: Add a private final Swerve
  private final Swerve swerve;

  // STEP 4: Add a private final XBoxController for the driver's controller
  private final CommandXboxController driverController;

  public RobotContainer() {

    // STEP 2: Initialize the Swerve
    swerve = new Swerve();

    // STEP 4: Initialize the driver's Joystick
    driverController = new CommandXboxController(0);

    configureBindings();
  }

  private void configureBindings() {

    // STEP 4: Set the Swerve default command to Swerve's driveFieldRelative command.
    // Pass in the CommandXBoxController supplier methods
    swerve.setDefaultCommand(
        swerve.driveFieldRelativeCommand(
            driverController::getLeftY, driverController::getLeftX, driverController::getRightX));
  }

  public void teleopInit() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
