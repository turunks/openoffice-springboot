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
		File file = new File("d:/海南省陵水县建设工程智慧工地信息监管服务平台实施方案-new.pptx");
		try {
			File newFile = new File("D:/test_pdf");
			if (!newFile.exists()) {
				newFile.mkdirs();
			}
			//文件转换
			converter.convert(file).to(new File("D:/test_pdf/test.pdf")).execute();
			//使用response，将pdf文件以流的方式发送到前端
			ServletOutputStream outputStream = response.getOutputStream();
			InputStream in = new FileInputStream(new File("D:/test_pdf/test.pdf"));
			int i = IOUtils.copy(in, outputStream);
			System.out.println(i);
			in.close();
			outputStream.close();
		} catch (Exception e) {
			throw new RuntimeException("转换失败");
		} 
	}
}
