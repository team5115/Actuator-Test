package frc.team5115.Robot;

import java.util.function.Function;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LedStrip extends SubsystemBase {
    private final int ledCount;
    private final AddressableLED leds;
    private final AddressableLEDBuffer buffer;
    
    public LedStrip(int port, int ledCount) {
        this.ledCount = ledCount;
        leds = new AddressableLED(port);
        leds.setLength(ledCount);
        buffer = new AddressableLEDBuffer(ledCount);
    }

    public void start() {
        leds.start();
    }

    public void stop() {
        leds.stop();
    }

    public void update() {

    }

    public void setUniformColor(int red, int green, int blue) {
        iterateAllLeds((index) -> {
            return new Color(red, green, blue);
        });
    }

    private void iterateAllLeds(Function<Integer, Color> function) {
        for (int i = 0; i < ledCount; i++) {
            buffer.setLED(i, function.apply(i));
        }
        leds.setData(buffer);
    }
}
