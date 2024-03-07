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
    PWM actuator8;
    PWM actuator9;

    @Override
    public void robotInit() {
        joy = new Joystick(0);

        actuator8 = new PWM(8);
        actuator8.setBoundsMicroseconds(2000, 1500, 1500, 1500, 1000);

        actuator9 = new PWM(9);
        actuator9.setBoundsMicroseconds(2000, 1500, 1500, 1500, 1000);

        new JoystickButton(joy, XboxController.Button.kA.value)
        .onTrue(new InstantCommand(this :: deploy));

        new JoystickButton(joy, XboxController.Button.kY.value)
        .onTrue(new InstantCommand(this :: stow));

        new JoystickButton(joy, XboxController.Button.kB.value)
        .onTrue(new InstantCommand(this :: epicButton));
    }

    private void stow() {
        actuator8.setPosition(0.85);
        actuator9.setPosition(0.85);
    }

    private void deploy(){
        actuator8.setPosition(0.25);
        actuator9.setPosition(0.25);
    }

    private void epicButton()
    {
        actuator8.setPosition(0.33);
        actuator9.setPosition(0.33);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        counter = 0;
        
    }

    int counter;

    @Override
    public void teleopPeriodic() {
        if (joy.getRawButton(XboxController.Button.kX.value)) {
            double input = (joy.getRawAxis(XboxController.Axis.kLeftY.value) + 1) / 2.0;
            actuator8.setPosition(input);
            actuator9.setPosition(input);
        }
        counter ++;

        if (counter % 20 == 0) {
            System.out.println("act8: " + actuator8.getPosition());
            System.out.println("act9: " + actuator9.getPosition());
        }
    }
}
