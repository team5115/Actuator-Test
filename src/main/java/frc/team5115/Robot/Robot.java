package frc.team5115.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Robot extends TimedRobot {
    Joystick joy;
    PWM actuator;

    @Override
    public void robotInit() {
        joy = new Joystick(0);
        actuator = new PWM(9);
        actuator.setBoundsMicroseconds(2000, 1500, 1500, 1500, 1000);

        new JoystickButton(joy, XboxController.Button.kA.value)
        .onTrue(new InstantCommand(this :: pressedA));

        new JoystickButton(joy, XboxController.Button.kB.value)
        .onTrue(new InstantCommand(this :: pressedB));
    }

    private void pressedA() {
        actuator.setPosition(0.75);
    }

    private void pressedB(){
        actuator.setPosition(0.1);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }
 
    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void testInit () {
    }

    @Override
    public void testPeriodic () {
    }

    @Override
    public void teleopInit() {
        
    }

    @Override
    public void teleopPeriodic() {
        if (joy.getRawButton(XboxController.Button.kX.value)) {
            actuator.setSpeed(joy.getRawAxis(XboxController.Axis.kLeftY.value));
        }
    }
}
