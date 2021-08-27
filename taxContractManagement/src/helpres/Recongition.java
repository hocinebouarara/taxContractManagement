/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpres;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.*;
import java.io.File;

/**
 *
 * @author Deeplight
 */
public class Recongition {

    public static String result;

    public static String recognition(int x, int y, int w, int h, String path) throws TesseractException {

        File f = new File(path);
        Rectangle rect = new Rectangle(x, y, w, h);
        Tesseract tess = new Tesseract();
        System.out.println("Ocr is trying to run ... ");
        tess.setDatapath("C:\\Users\\Deeplight\\Downloads\\Tess4J-3.4.8-src\\Tess4J\\tessdata");

        String result = tess.doOCR(f, rect);
        System.out.println("Do-Ocr finish his work!!");
        return result;

    }

}
