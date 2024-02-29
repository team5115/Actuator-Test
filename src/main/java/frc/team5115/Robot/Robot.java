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
        .onTrue(new InstantCommand(this :: deploy));

        new JoystickButton(joy, XboxController.Button.kB.value)
        .onTrue(new InstantCommand(this :: stow));
    }

    private void stow() {
        actuator.setPosition(0.661);
    }

    private void deploy(){
        actuator.setPosition(0.100);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        
    }

    @Override
    public void teleopPeriodic() {
        if (joy.getRawButton(XboxController.Button.kX.value)) {
            double input = (joy.getRawAxis(XboxController.Axis.kLeftY.value) + 1) / 2.0;
            actuator.setPosition(input);
        }
        System.out.println(actuator.getPosition());
    }
}
