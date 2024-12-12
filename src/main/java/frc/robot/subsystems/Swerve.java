package frc.robot.subsystems;

// STEP 6: import static Meters and Radians

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.SwerveConstants;
import java.util.function.DoubleSupplier;

// STEP 2: Add the epilogue @Logged annotation
// STEP 2: Extend Swerve with SubsystemBase
@Logged
public class Swerve extends SubsystemBase {

  // STEP 5: Add a prviate Pose2d estimatedPose. This should automatically get picked up for logging
  private Pose2d estimatedPose;

  public Swerve() {

    // STEP 5: Initialize the Pose2d. An empty constructor will set the robot at (0,0) on the field,
    // facing to the right.
    estimatedPose = new Pose2d();
  }

  /**
   * Step 3a: Private Method driveFieldRelative that takes field-relative inputs
   *
   * @param X field-relative X linear velocity in meters/s
   * @param Y field-relative Y linear velocity in meters/s
   * @param Omega field-relative angular velocity in radians/s
   */
  private void driveFieldRelative(LinearVelocity x, LinearVelocity y, AngularVelocity omega) {
    // STEP 5: In the driveFieldRelative update the estimatedPose by scaling the input
    // parameters and adding them to the current pose values.

    // STEP 6: Update the estimated pose using the requested field-relative velocities
    // The process of converting AngularVelocity into a Rotation2d is comp

    estimatedPose =
        new Pose2d(
            x.times(SwerveConstants.swerveUpdatePeriod).plus(estimatedPose.getMeasureX()),
            y.times(SwerveConstants.swerveUpdatePeriod).plus(estimatedPose.getMeasureY()),
            estimatedPose
                .getRotation()
                .plus(new Rotation2d(omega.times(SwerveConstants.swerveUpdatePeriod))));
  }

  /**
   * Step 3b: Public command factory driveFieldRelativeCommand that takes field-relative inputs and
   * returns a command that passes the input parameters into the private driveFieldRelative
   *
   * @param X field-relative X with relative range of -1.0 to 1.0
   * @param Y field-relative Y with relative range of -1.0 to 1.0
   * @param Omega field-relative omega with relative range of -1.0 to 1.0
   */
  public Command driveFieldRelativeCommand(
      DoubleSupplier x, DoubleSupplier y, DoubleSupplier omega) {

    // STEP 6: Convert the relative Joystick inputs to Velocities
    return Commands.run(
        () ->
            this.driveFieldRelative(
                Constants.SwerveConstants.MAX_SPEED.times(-x.getAsDouble()),
                Constants.SwerveConstants.MAX_SPEED.times(-y.getAsDouble()),
                Constants.SwerveConstants.MAX_ANGULAR_VELOCITY.times(-omega.getAsDouble())),
        this);
  }
}
