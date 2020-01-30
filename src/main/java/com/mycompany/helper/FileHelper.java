package com.mycompany.helper;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileHelper {
	public static String upload(String uploadPath, MultipartFile multipartFile, HttpServletRequest request) {
		String uploadedFileUrl = null;
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		System.out.println("rootPath: " + rootPath);
		String realUploadPath = rootPath + "/resources" + uploadPath;
		System.out.println("realPath: " + realUploadPath);
		File dir = new File(realUploadPath);

		if (!dir.exists())
			dir.mkdirs();

		File file = new File(dir.getAbsolutePath() + File.separator + multipartFile.hashCode()
				+ multipartFile.getOriginalFilename());
		try {
			multipartFile.transferTo(file);
			String contextPath = request.getContextPath();
			uploadedFileUrl = contextPath + uploadPath + File.separator + file.getName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uploadedFileUrl;
	}
}
