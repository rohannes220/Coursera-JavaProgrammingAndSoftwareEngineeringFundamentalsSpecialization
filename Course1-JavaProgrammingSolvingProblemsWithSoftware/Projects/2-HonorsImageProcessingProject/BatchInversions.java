import edu.duke.*;
import java.io.*; 

public class BatchInversions
{
   
    public ImageResource makeInversion(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage); 
        for(Pixel p: outImage.pixels()){
            int newRed = 255 - p.getRed();
            int newBlue = 255 - p.getBlue();
            int newGreen = 255- p.getGreen(); 
            p.setRed(newRed); 
            p.setBlue(newBlue);
            p.setGreen(newGreen);
        }
        return outImage; 
    }
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();  
        for(File f: dr.selectedFiles()){
            ImageResource inImage  = new ImageResource(f); 
            ImageResource invertedImage = makeInversion(inImage); 
            String fileName = f.getName(); 
            String invertedFileName = "inverted-" + fileName;
            invertedImage.setFileName(invertedFileName); 
            invertedImage.save();
        }
    }
}
