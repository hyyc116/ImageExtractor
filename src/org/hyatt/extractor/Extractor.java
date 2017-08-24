package org.hyatt.extractor;

import java.io.File;
/***
 * 程序主入口
 * @author huangyong
 *
 */
public class Extractor {
	
	
	public static void extract(String type,String indir,String outdir){
		ImageExtractor extractor=null;
		if(type.equals("pdf")){
			extractor = new PdfExtractor();
		}else if(type.equals("word")){
			extractor = new WordExtractor();
		}else{
			System.err.println("不支持该格式文档，请输入pdf 或者 word!");
		}
		File directory = new File(indir);
		if(!directory.isDirectory()){
			System.err.println("请输入正确的输入文件夹地址！");
		}
		for(String path:directory.list()){
			String ab_path = indir+File.separator+path;
			extractor.extractImages(ab_path, outdir);
		}
	}
	
	public static void main(String[] args) {
		if(args.length!=3){
			System.err.println("错误的参数，java -jar image_extractor.jar [pdf/word] [in dir] [out dir] )");
			System.exit(0);
		}else{
			Extractor.extract(args[0], args[1], args[2]);
		}
	}

}
