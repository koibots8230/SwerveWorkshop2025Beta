package frc.robot;

import static edu.wpi.first.units.Units.*;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Time;

public class Constants {

  public static class RobotConstants {}

  // STEP 6: Add a LinearVelocity MAX_SPEED and a AngularVelocity MAX_ANGULAR_VELOCITY
  public static class SwerveConstants {
    public static LinearVelocity MAX_SPEED = MetersPerSecond.of(4.0);
    public static AngularVelocity MAX_ANGULAR_VELOCITY = DegreesPerSecond.of(360.0);
    public static Time swerveUpdatePeriod = Millisecond.of(20.0);
  }
}
