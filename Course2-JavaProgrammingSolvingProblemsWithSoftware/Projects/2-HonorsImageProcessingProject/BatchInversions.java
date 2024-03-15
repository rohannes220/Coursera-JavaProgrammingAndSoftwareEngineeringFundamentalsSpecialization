import edu.duke.*;
import java.io.*;

public class BatchInversions {
    // given an image return a inverted version of it
    public ImageResource makeInversion(ImageResource inImage) {
        // we are storing a copy of the input in outiMAGE so that we dont have to find
        // the x and y or its width and height
        ImageResource outImage = new ImageResource(inImage);

        // we will be returning the outimage since the outimage is basically the inimage
        // we make changes to its pixelation
        // to set outimages pixels, we take the pixels from inimage representing by the
        // for loop and we subtract that from 255
        // these values are outImage's new pixelations, since outImage is a copy of
        // inImage we modify inImage's pixels to give us a new image
        for (Pixel p : outImage.pixels()) {
            int newRed = 255 - p.getRed();
            int newBlue = 255 - p.getBlue();
            int newGreen = 255 - p.getGreen();

            // set each pixel to outImage
            p.setRed(newRed);
            p.setBlue(newBlue);
            p.setGreen(newGreen);
        }

        // return the new Image
        return outImage;
    }

    // select some files, convert it to inverted and save it
    public void selectAndConvert() {
        // use this for multiple files
        DirectoryResource dr = new DirectoryResource();
        // for every file in the dr
        for (File f : dr.selectedFiles()) {
            // take the orginialImage store it in inImage
            ImageResource inImage = new ImageResource(f);

            // call the inversion function on inImage
            ImageResource invertedImage = makeInversion(inImage);

            // get the file name and set new file name for the inverted image.
            String fileName = f.getName();
            String invertedFileName = "inverted-" + fileName;
            invertedImage.setFileName(invertedFileName);

            // Since the invertedImage is being called on the inImage, the files will be
            // saved in that same path
            invertedImage.save();
        }
    }
}
