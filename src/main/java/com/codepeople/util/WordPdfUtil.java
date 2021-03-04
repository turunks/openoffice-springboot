package com.codepeople.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

/**
 * Word 转 Pdf 帮助类
 *
 * @author admin
 *
 */
public  class WordPdfUtil {
    private static boolean license = false;

//    public static void main(String[] args) {
////        doc2pdf("C:\\Users\\admin\\Desktop\\doc\\111.docx","C:\\Users\\admin\\Desktop\\doc\\aaa.pdf");
////        doc2pdf("C:\\Users\\admin\\Desktop\\doc\\222.docx","C:\\Users\\admin\\Desktop\\doc\\bbb.pdf");
////        doc2pdf("C:\\Users\\admin\\Desktop\\doc\\333.docx","C:\\Users\\admin\\Desktop\\doc\\ccc.pdf");
////        doc2pdf("/root/micro/333.docx","/root/micro/ccc.pdf");
//        doc2pdf("d:/testword/工作任务单2020121601004.docx","d:/testword/工作任务单2020121601004.pdf");
//    }

//    static {
//        try {
//            // license.xml放在src/main/resources文件夹下
//            InputStream is = WordPdfUtil.class.getClassLoader().getResourceAsStream("license.xml");
//            License aposeLic = new License();
//            aposeLic.setLicense(is);
//            license = true;
//        } catch (Exception e) {
//            license = false;
//            System.out.println("License验证失败...");
//            e.printStackTrace();
//        }
//    }

    /**
     * doc转pdf
     *
     * @param wordPath
     * @param pdfPath
     */
    public static void doc2pdf(String wordPath, String pdfPath) {
        // 验证License 若不验证则转化出的pdf文档会有水印产生
//        if (!license) {
//            System.out.println("License验证不通过...");
//            return;
//        }

        try {
            long old = System.currentTimeMillis();
            //新建一个pdf文档
            File file = new File(pdfPath);
            FileOutputStream os = new FileOutputStream(file);
            //Address是将要被转化的word文档
            Document doc = new Document(wordPath);
            //全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            doc.save(os, SaveFormat.PDF);
            long now = System.currentTimeMillis();
            os.close();
            //转化用时
            System.out.println("Word 转 Pdf 共耗时：" + ((now - old) / 1000.0) + "秒");
        } catch (Exception e) {
            System.out.println("Word 转 Pdf 失败...");
            e.printStackTrace();
        }
    }
}
