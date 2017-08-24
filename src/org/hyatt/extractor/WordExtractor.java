package org.hyatt.extractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
/**
 * 抽取word文档中的图片
 * @author hyatttt
 * @2017年8月24日
 *
 */
public class WordExtractor implements ImageExtractor {

	/**
	 * 这个应该是抽取2007年以前的word文档
	 * @param path
	 * @param outdir
	 */
	@Deprecated
	public void extract(String path, String outdir) {
		try {
			File docfile = new File(path);
			HWPFDocument doc = new HWPFDocument(new FileInputStream(docfile));
			List<Picture> pics = doc.getPicturesTable().getAllPictures();
			for (int i = 0; i < pics.size(); i++) {
				Picture pic = (Picture) pics.get(i);
				String outname = outdir + "/" + docfile.getName().replace(".", "_") + "_" + i + "."
						+ pic.suggestFileExtension();
				FileOutputStream outputStream = new FileOutputStream(outname);
				outputStream.write(pic.getContent());
				outputStream.close();
			}
			doc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// 2007年以后的word文档即使是doc格式也可以抽取
	@Override
	public void extractImages(String path, String outdir) {
		File file = new File(path);
		try {
			FileInputStream fis = new FileInputStream(file);
			XWPFDocument document = new XWPFDocument(fis);
			XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(document);
			//获得
//			String text = xwpfWordExtractor.getText();
//			System.out.println(text);
			List<XWPFPictureData> picList = document.getAllPictures();
			for (int i = 0; i < picList.size(); i++) {
				XWPFPictureData pic = picList.get(i);
//				System.out.println(pic.getPictureType() + File.separator + pic.suggestFileExtension() + File.separator
//						+ pic.getFileName());
				byte[] bytev = pic.getData();
				String outname = outdir + "/" + file.getName().replace(".", "_") + "_" + i + "."
						+ pic.suggestFileExtension();
				FileOutputStream fos = new FileOutputStream(outname);
				
				fos.write(bytev);
				fos.close();
				System.err.println("saved to:"+outname);
				
			}
			xwpfWordExtractor.close();
			fis.close();
		} catch (Exception e) {
			System.err.println("error path:"+path+",msg:"+e.getMessage().toString());
		}
	}
}
