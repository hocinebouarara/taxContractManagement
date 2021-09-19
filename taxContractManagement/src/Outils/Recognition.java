package Outils;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.*;
import java.io.File;

public class Recognition {

    public static String result;

    public static String recognition(int x, int y, int w, int h, String path) throws TesseractException {

                File f = new File(path);
                Rectangle rect = new Rectangle(x,y, w , h);
                Tesseract tess = new Tesseract();
                System.out.println("Ocr is trying to run ... ");
                tess.setDatapath("C:\\Users\\Deeplight\\Downloads\\Tess4J-3.4.8-src\\Tess4J\\tessdata");
                String result = tess.doOCR(f,rect);
                System.out.println("Do-Ocr finish his work!!");
                return result;

    }
}
