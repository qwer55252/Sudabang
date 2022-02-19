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

        BufferedImage img = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_RGB);
        c.paint(img.getGraphics());
        try {
            ImageIO.write(img, "png", saveFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
