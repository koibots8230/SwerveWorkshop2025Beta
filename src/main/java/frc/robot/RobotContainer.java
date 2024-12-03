// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

// STEP 1: Add the epilogue @Logged annotation
public class RobotContainer {

  // STEP 4: Add a private final XBoxController for the driver's controller

  public RobotContainer() {

    // STEP 4: Initialize the driver's Joystick

    configureBindings();
  }

  private void configureBindings() {

    // STEP 4: Set the Swerve default command to Swerve's driveFieldRelative command.
    // Pass in the XBoxController values and add a Deadband
  }

  public void teleopInit() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
