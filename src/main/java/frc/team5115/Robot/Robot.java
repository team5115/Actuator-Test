package frc.team5115.Robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Robot extends TimedRobot {
    private final int LED_COUNT = 20;
    Joystick joy;
    AddressableLED addressableLED;
    AddressableLEDBuffer buffer;
    boolean hasNote;
    int currentIndex;

    @Override
    public void robotInit() {
        joy = new Joystick(0);
        addressableLED = new AddressableLED(7);
        addressableLED.setLength(LED_COUNT);
        buffer = new AddressableLEDBuffer(LED_COUNT);
        addressableLED.setData(buffer);
        addressableLED.start();

        new JoystickButton(joy, 1)
        .onTrue(new InstantCommand(this :: toggleNote));
    }

    private void toggleNote() {
        hasNote = !hasNote;
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void disabledInit() {
        for (int i = 0; i < LED_COUNT; i ++) {
            buffer.setRGB(i, 0, 0, 0);
        }
        addressableLED.setData(buffer);
        currentIndex = 0;
    }

    @Override
    public void teleopInit() {
        hasNote = false;
    }

    @Override
    public void teleopPeriodic() {
        for (int i = 0; i < LED_COUNT; i ++) {
            updateLed(i);
        }
        addressableLED.setData(buffer);
        currentIndex++;
        if (currentIndex >= LED_COUNT) {
            currentIndex = 0;
        }
    }

    private void updateLed(int index) {
        // if (hasNote) {
        //     buffer.setRGB(index, 0, 150, 0);
        // } else {
        //     buffer.setRGB(index, 150, 0, 0);
        // }
        double delta = Math.abs(index - currentIndex);
        double applied = 0;
        if (delta < 5) {
            applied = delta / 5;
        }
        buffer.setRGB(index, (int) applied, 0, 0);
    }
}
