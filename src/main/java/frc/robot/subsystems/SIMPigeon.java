package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Rotation2d;

public class SIMPigeon {
  private double yaw = 0.0;

  public SIMPigeon() {}

  public double getYaw() {
    return yaw;
  }

  public void setYaw(double degrees) {
    yaw = degrees;
  }

  public Rotation2d getRotation2d() {
    return new Rotation2d(yaw * Math.PI / 180.0);
  }

  public void accumulateYaw(double degrees) {
    yaw += degrees;
  }
}
