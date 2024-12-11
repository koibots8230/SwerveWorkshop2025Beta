package frc.robot.subsystems;

// STEP 6: import static Meters and Radians from the wpi Units library

// STEP 2: Add the epilogue @Logged annotation
// STEP 2: Extend Swerve with SubsystemBase
public class Swerve {

  // STEP 5: Add a prviate Pose2d estimatedPose. This should automatically get picked up for logging

  public Swerve() {

    // STEP 5: Initialize the Pose2d. An empty constructor will set the robot at (0,0) on the field,
    // facing to the right.

  }

  /**
   * Step 3a: Private Method driveFieldRelative that takes field-relative inputs
   *
   * @param X field-relative X linear velocity in meters/s
   * @param Y field-relative Y linear velocity in meters/s
   * @param Omega field-relative angular velocity in radians/s
   */

  // STEP 5: In the driveFieldRelative update the estimatedPose by adding the inputs to the pose

  // STEP 6: Update the estimated pose using the requested field-relative velocities
  // The process of converting AngularVelocity into a Rotation2d is comp

  /**
   * Step 3b: Public command factory driveFieldRelativeCommand that takes field-relative inputs and
   * returns a command that passes the input parameters into the private driveFieldRelative
   *
   * @param X field-relative X with range of -1.0 to 1.0
   * @param Y field-relative Y with range of -1.0 to 1.0
   * @param Omega field-relative omega with range of -1.0 to 1.0
   */

  // STEP 6: Convert the relative Joystick inputs to Velocities by multiplying the input by the
  // MAX Speeds
}
