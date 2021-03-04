/**
 * @Title: OfficeController.java
 * @Package com.codepeople.controller
 * @Description: 
 * Copyright: Copyright (c) 2019 www.codepeople.cn Inc. All rights reserved. 
 * Website: www.codepeople.cn
 * 注意：本内容仅限于海南科澜技术信息有限公司内部传阅，禁止外泄以及用于其他的商业目 
 * @Author 刘仁
 * @DateTime 2019年11月19日 上午10:37:49
 * @version V1.0
 */

package com.codepeople.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.codepeople.util.WordPdfUtil;
import org.apache.commons.io.IOUtils;
import org.jodconverter.DocumentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: OfficeController
 * @Description: 
 * @Author 刘仁
 * @DateTime 2019年11月19日 上午10:37:49 
 */
@Controller
@RequestMapping("/office")
public class OfficeController {
	@Autowired
	private DocumentConverter converter;
	@Autowired
	private HttpServletResponse response;

	@RequestMapping("/office2pdf")
	public void office2Pdf() {
		String path = "d:/testword/竣工资料（李精-李航湖）170334573.doc";
		File file = new File(path);
		try {
			File newFile = new File("D:/testword");
			if (!newFile.exists()) {
				newFile.mkdirs();
			}
			//文件转换
			// 是doc用aspose.words转pdf,反之openoffice
			if(path.endsWith("doc")){
				WordPdfUtil.doc2pdf("d:/testword/竣工资料（李精-李航湖）170334573.doc","d:/testword/竣工资料（李精-李航湖）170334573.pdf");
			}else {
				converter.convert(file).to(new File("D:/testword/工作任务单(1)2020121101046.pdf")).execute();
			}
//
			//使用response，将pdf文件以流的方式发送到前端
			ServletOutputStream outputStream = response.getOutputStream();
			InputStream in = new FileInputStream(new File("d:/testword/竣工资料（李精-李航湖）170334573.pdf"));
			int i = IOUtils.copy(in, outputStream);
			System.out.println(i);
			in.close();
			outputStream.close();
		} catch (Exception e) {
			throw new RuntimeException("转换失败");
		} 
	}


	private void doc2pdf(){

	}
}
