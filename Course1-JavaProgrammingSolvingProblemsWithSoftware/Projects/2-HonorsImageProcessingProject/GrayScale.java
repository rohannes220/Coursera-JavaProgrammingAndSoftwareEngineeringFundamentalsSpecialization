import edu.duke.*;
import java.io.*;

public class GrayScale {
    // take an image and convert it to fully gray
    public ImageResource convertImageToGray(ImageResource inImage) {
        // we will modify inImage's pixels to make it gray.
        // to make the pixel gray, find the average of the red blue and green
        // set that average for each pixels new values and return image
        for (Pixel inPixel : inImage.pixels()) {
            // find the average
            int red = inPixel.getRed();
            int green = inPixel.getGreen();
            int blue = inPixel.getBlue();
            int average = (red + green + blue) / 3;

            // set each pixel value to average
            inPixel.setRed(average);
            inPixel.setBlue(average);
            inPixel.setGreen(average);
        }
        // return the iamge
        return inImage;
    }

    public void testGrayConverter() {
        ImageResource inImage = new ImageResource();
        ImageResource result = convertImageToGray(inImage);
        result.draw();
    }

    // take some files and convert the image and save it
    public void batchConvertAndSave() {
        // hold the files in a directory resource
        DirectoryResource dr = new DirectoryResource();
        // loop over each file
        for (File f : dr.selectedFiles()) {
            // store the iamge file in inimage and get the filename
            ImageResource inImage = new ImageResource(f);
            String fileName = f.getName();

            // convert the image to grey and save the file according to the new name rules
            ImageResource grayImage = convertImageToGray(inImage);
            String newGrayFileName = "gray-" + fileName;

            // the file will be saved in the original folder as inimage because the function
            // is being called on inimage
            grayImage.setFileName(newGrayFileName);
            grayImage.save();
        }
    }
}
