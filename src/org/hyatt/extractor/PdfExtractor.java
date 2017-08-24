package org.hyatt.extractor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
/**
 * 抽取PDF中的图片 
 * @author hyatttt
 * @2017年8月24日
 *
 */
public class PdfExtractor implements ImageExtractor {
	/**
	 * 从PDF抽取image
	 * 
	 * @param pdfPath
	 * @throws IOException
	 * @throws InvalidPasswordException
	 */
	@Override
	public void extractImages(String path,String outdir) {
		try {
			File pdffile = new File(path);
			PDDocument document = PDDocument.load(pdffile);

			PDFRenderer pdfRenderer = new PDFRenderer(document);
			for (int i = 0; i < document.getPages().getCount(); i++) {
				BufferedImage bim = pdfRenderer.renderImage(i, 1.0f, ImageType.RGB);
				File outfile = new File(outdir+"/"+pdffile.getName().replace(".", "_")+"_"+i+".jpg");
				ImageIO.write(bim, "jpg", outfile);
			}
			document.close();
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // filePath is the path to your .pdf

	}
}
