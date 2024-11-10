package frc.robot.subsystems;

import static edu.wpi.first.units.Units.*;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.epilogue.NotLogged;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

@Logged
public class Swerve extends SubsystemBase {

  private final Distance WIDTH = Inches.of(26.0);
  private final Distance LENGTH = Inches.of(26.0);
  @NotLogged private final SwerveDriveKinematics swerveDriveKinematics;
  @NotLogged private final SwerveDrivePoseEstimator swerveDrivePoseEstimator;
  private final SwerveModule[] swerveModules;

  private Pose2d estimatedPose;
  private SwerveModuleState[] demandModuleStates;
  private SwerveModuleState[] currentModuleStates;
  private SwerveModulePosition[] modulePositions;
  @NotLogged private SIMPigeon simPigeon;

  public Swerve() {
    swerveDriveKinematics =
        new SwerveDriveKinematics(
            new Translation2d(LENGTH.divide(2), WIDTH.divide(2)),
            new Translation2d(LENGTH.divide(2), WIDTH.divide(-2)),
            new Translation2d(LENGTH.divide(-2), WIDTH.divide(2)),
            new Translation2d(LENGTH.divide(-2), WIDTH.divide(-2)));
    estimatedPose = new Pose2d();

    swerveModules = new SwerveModule[4];
    modulePositions = new SwerveModulePosition[4];
    demandModuleStates = new SwerveModuleState[4];
    currentModuleStates = new SwerveModuleState[4];

    for (int i = 0; i < 4; i++) {
      swerveModules[i] = new SwerveModule();
      demandModuleStates[i] = new SwerveModuleState();
      currentModuleStates[i] = new SwerveModuleState();
      modulePositions[i] = swerveModules[i].getPosition();
    }

    simPigeon = new SIMPigeon();
    simPigeon.setYaw(0.0);

    swerveDrivePoseEstimator =
        new SwerveDrivePoseEstimator(
            swerveDriveKinematics, simPigeon.getRotation2d(), modulePositions, estimatedPose);
  }

  public void drive(double x, double y, double z) {
    simPigeon.accumulateYaw(z * 5.0);
    ChassisSpeeds chassisSpeeds =
        ChassisSpeeds.fromFieldRelativeSpeeds(x * 3.0, y * 3.0, z, simPigeon.getRotation2d());
    demandModuleStates = swerveDriveKinematics.toSwerveModuleStates(chassisSpeeds);
    for (int i = 0; i < 4; i++) {
      swerveModules[i].setState(demandModuleStates[i]);
      currentModuleStates[i] = swerveModules[i].getState();
      modulePositions[i] = swerveModules[i].getPosition();
    }
    estimatedPose = swerveDrivePoseEstimator.update(simPigeon.getRotation2d(), modulePositions);
  }

  public void resetHeading(double degrees) {
    System.out.println("Reset Heading to: " + degrees);
    simPigeon.setYaw(degrees);
  }
}
