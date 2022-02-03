package Student;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CaptureJFrame {
    public CaptureJFrame(Container c, String captureFilePath) {
        // Create test file
        File saveFile = new File(captureFilePath);

        // Use the ImageIO API to write the bufferedImage to a temporary file
        try {
            BufferedImage im = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = im.createGraphics();
            c.printAll(g2d);

            g2d.dispose();
            ImageIO.write(im, "png", saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
