/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Test {
	public static void main(String[] args)
	{
		Tesseract tesseract = new Tesseract();
		try {

			tesseract.setDatapath("C:\\Users\\Deeplight\\Downloads\\Tess4J-3.4.8-src\\Tess4J\\tessdata");

			// the path of your tess data folder
			// inside the extracted file
			String text = tesseract.doOCR(new File("C:\\Users\\Deeplight\\OneDrive\\Pictures\\Captures d’écran\\he.PNG"));

			// path of your image file
			System.out.println("the text is :"+text);
		}
		catch (TesseractException e) {
			e.printStackTrace();
		}
	}
}