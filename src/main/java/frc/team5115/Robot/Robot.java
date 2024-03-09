package frc.team5115.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Robot extends TimedRobot {
    Joystick joy;
    LedStrip ledStrip;
    boolean hasNote;

    @Override
    public void robotInit() {
        joy = new Joystick(0);
        ledStrip = new LedStrip(7, 20);

        new JoystickButton(joy, 1).onTrue(new InstantCommand(this :: toggleNote));
    }

    private void toggleNote() {
        hasNote = !hasNote;
        if (hasNote) {
            ledStrip.setUniformColor(0, 150, 0);
        } else {
            ledStrip.setUniformColor(150, 0, 0);
        }
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void disabledInit() {
        ledStrip.setUniformColor(0, 0, 0);
    }

    @Override
    public void teleopInit() {
        ledStrip.updateKnightRider();
    }

    @Override
    public void teleopPeriodic() {
        
    }
}
