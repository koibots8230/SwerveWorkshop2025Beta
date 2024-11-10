package frc.robot.subsystems;

import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public class SwerveModule {

  private SwerveModuleState state = new SwerveModuleState();
  private double distance = 0.0;

  public SwerveModule() {}

  public void setState(SwerveModuleState state) {
    this.state = state;
    distance += state.speedMetersPerSecond / 50.0;
  }

  public SwerveModuleState getState() {
    return state;
  }

  public SwerveModulePosition getPosition() {
    return new SwerveModulePosition(distance, state.angle);
  }
}
