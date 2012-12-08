package jtictactoe;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author aNNiMON
 */
public class Util {

    public static BufferedImage getImage(String path) {
        BufferedImage image = null;
        try {
            InputStream is = Runtime.getRuntime().getClass().getResourceAsStream(path);
            image = ImageIO.read(is);
            is.close();
        } catch (IOException ex) {
        }
        return image;
    }
    
}
