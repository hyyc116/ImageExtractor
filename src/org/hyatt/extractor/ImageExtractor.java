package org.hyatt.extractor;

/**
 * 从 pdf 以及 word里面抽取所有的图片
 * 
 * @author huangyong
 *
 */
public interface ImageExtractor {
	
	// 从文件中抽取图像
	public abstract void extractImages(String path,String outdir);

}
