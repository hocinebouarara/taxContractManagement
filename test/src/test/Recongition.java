/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Rectangle;
import java.io.File;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Deeplight
 */
class Recongition {

    public static String recognition(int x, int y, int w, int h, String path) throws TesseractException {

        File f = new File(path);
        Rectangle rect = new Rectangle(x,(h-10)*(w-10));
        Tesseract tess = new Tesseract();
        System.out.println("Ocr is trying to run ... ");
        tess.setDatapath("C:\\Users\\Deeplight\\Downloads\\Tess4J-3.4.8-src\\Tess4J\\tessdata");
        tess.setLanguage("fra");
        String result = tess.doOCR(f, rect);
        System.out.println("Do-Ocr finish his work!!");
        return result;

    }
}
