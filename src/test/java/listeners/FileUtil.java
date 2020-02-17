package listeners;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Date;

public class FileUtil {

    private static FileUtil instance;

    public static String convertImageToBase64(File imageFile) {
        String image = null;
        BufferedImage buffImage = null;
        try {
            buffImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (buffImage != null) {
            java.io.ByteArrayOutputStream os = new
                    java.io.ByteArrayOutputStream();
            try {
                ImageIO.write(buffImage, "png", os);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] data = os.toByteArray();
            image = Base64.encodeBase64String(data);
        }
        return "data:image/png;base64," + image;
    }

    private static synchronized void createInstance() {
        if (instance == null) {
            instance = new FileUtil();
        }
    }


}
