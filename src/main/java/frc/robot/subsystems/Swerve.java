package frc.robot.subsystems;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

@Logged
public class Swerve extends SubsystemBase {

  @Logged private Pose2d estimatedPose = new Pose2d();

  public Swerve() {
    estimatedPose = new Pose2d();
  }

  public void driveFieldOriented(ChassisSpeeds chassisSpeeds) {}
}
