package frc.team5115.Robot;

import java.util.function.Function;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LedStrip extends SubsystemBase {
    private final int ledCount;
    private final AddressableLED leds;
    private final AddressableLEDBuffer buffer;

    private int counter;
    private int direction;
    
    public LedStrip(int port, int ledCount) {
        this.ledCount = ledCount;
        leds = new AddressableLED(port);
        leds.setLength(ledCount);
        buffer = new AddressableLEDBuffer(ledCount);

        counter = 0;
        direction = 0;
    }

    public void start() {
        leds.start();
    }

    public void stop() {
        leds.stop();
    }

    public void updateKnightRider() {
        counter += direction;
        if (counter == ledCount || counter == -1) {
            direction = -direction;
            counter += 2 * direction;
        }
        iterateAllLeds((index) -> {
            double percent = 0;
            double delta = Math.abs(counter - index);
            final double maxDelta = 2.0;
            if (delta <= maxDelta) {
                percent = delta / maxDelta;
            }
            return new Integer[] { (int)(percent * 200), 0, 0 };
        });
    }

    public void setUniformColor(int red, int green, int blue) {
        iterateAllLeds((index) -> {
            return new Integer[] {red, green, blue};
        });
    }

    private void iterateAllLeds(Function<Integer, Integer[]> function) {
        for (int i = 0; i < ledCount; i++) {
            Integer[] color = function.apply(i);
            buffer.setRGB(i, color[0], color[1], color[2]);
        }
        leds.setData(buffer);
    }
}
