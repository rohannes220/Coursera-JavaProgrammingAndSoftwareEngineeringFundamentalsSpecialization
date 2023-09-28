import edu.duke.*;
import java.io.*;

public class GrayScale {
    public ImageResource convertImageToGray(ImageResource inImage) {
        // ImageResource outImage = new
        // ImageResource(inImage.getWidth(),inImage.getHeight());
        for (Pixel inPixel : inImage.pixels()) {
            int red = inPixel.getRed();
            int green = inPixel.getGreen();
            int blue = inPixel.getBlue();
            int average = (red + green + blue) / 3;
            inPixel.setRed(average);
            inPixel.setBlue(average);
            inPixel.setGreen(average);
        }
        return inImage;
    }

    public void testGrayConverter() {
        ImageResource inImage = new ImageResource();
        ImageResource result = convertImageToGray(inImage);
        result.draw();
    }

    public void batchConvertAndSave() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            String fileName = f.getName();

            ImageResource grayImage = convertImageToGray(inImage);
            String newGrayFileName = "gray-" + fileName;
            grayImage.setFileName(newGrayFileName);
            grayImage.save();
        }
    }
}
