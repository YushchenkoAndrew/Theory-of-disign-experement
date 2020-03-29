package article;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public abstract class ImageReaderWriter {
    private File file;
    public BufferedImage imageReader;
    public int[][][] pixelsColor;       //In each case it will be variable of Alpha Red Green Blue

    public ImageReaderWriter() {

    }

    public ImageReaderWriter(String path) {
        file = new File(path);
    }

    private void loadImage() throws Exception {
            imageReader = ImageIO.read(file);
            pixelsColor = new int[imageReader.getHeight()][imageReader.getWidth()][4];
        for (int i = 0; i < imageReader.getWidth(); i++)            //It's parameter let us variable from OX axes
            for (int j = 0; j < imageReader.getHeight(); j++) {      //It's parameter let us variable from OY axes
                int store = imageReader.getRGB(i, j);
//                pixelsColor[i][j][0] = (store >> 24) & 0xFF;
                pixelsColor[j][i][1] = (store >> 16) & 0xFF;
//                pixelsColor[i][j][2] = (store >> 8) & 0xFF;
//                pixelsColor[i][j][3] = store & 0xFF;
            }
    }

    public void readImage() {
        try {
            loadImage();
        }
        catch (Exception ex) {
            System.err.println("File  path = " + file.getPath() + "Not Found!!");
        }
    }

    public void setImageReaderPath(String path) {
        file = new File(path);
    }
}
