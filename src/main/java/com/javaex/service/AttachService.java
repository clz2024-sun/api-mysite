package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.vo.AttachVo;
import com.javaex.vo.AttachVo2;



@Service
public class AttachService {
	
	public String exeUpload2(AttachVo2 attchVo2) {
		System.out.println("AttachService.upload2");
		MultipartFile file = attchVo2.getImg();
		
		//파일저장경로 변수
		String saveDir;	
		
		//현재 os
		String osName = System.getProperty("os.name").toLowerCase();
	
		
		//사진에 기본정보로 우리가 관리할 정보를 뽑아내야된다 -->db저장
		//파일 저장 폴더
		if(osName.contains("linux")) {
			System.out.println("리눅스");
			/* saveDir = "/home/ec2-user/upload"; */
			saveDir = "/app/upload";
		}else {
			System.out.println("윈도우");
			saveDir =  "C:\\javaStudy\\upload";
		}
		
		//오리지날 파일명
		String orgName = file.getOriginalFilename();
		System.out.println("orgName: " + orgName );
		
		//확장자
		String exeName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println("exeName: " + exeName);
		
		//파일사이즈
		long fileSize = file.getSize();
		System.out.println("FileSize: " + fileSize);
		
		//저장파일명(겹치지 않아야 한다)
		String savaName = System.currentTimeMillis() + UUID.randomUUID().toString()+exeName;
		System.out.println("savaName: " + savaName);
		
		//파일 전체 경로+파일명
		String filePath = saveDir + File.separator + savaName;
		System.out.println("filePath: " + filePath);
		
		//(1)db저장
		//(1-1) 데이타 묶기
		//AttachVo attachVo= new AttachVo(orgName, savaName, filePath, fileSize);
		attchVo2.setOrgName(orgName);
		attchVo2.setSavaName(savaName);
		attchVo2.setFilePath(filePath);
		attchVo2.setFileSize(fileSize);
		
		System.out.println(attchVo2);
		
        //(1-2) dao를 통해서 db에저장
		//과제.....
		System.out.println("과제:" + "db저장중.......");
		
		
		//(2)사진을 서버(강남)에 하드디스크에 복사해야된다
		//파일저장
		try {
			byte[] fileData = file.getBytes();
			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			bos.write(fileData);
			bos.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return savaName;  //시간+uuid+.jpg
	}
	
	
	public String exeUpload(MultipartFile file) {
		System.out.println("AttachService.upload");
		
		//사진에 기본정보로 우리가 관리할 정보를 뽑아내야된다 -->db저장
		//파일 저장 폴더
		String saveDir = "C:\\javaStudy\\upload";
		
		
		//오리지날 파일명
		String orgName = file.getOriginalFilename();
		System.out.println("orgName: " + orgName );
		
		//확장자
		String exeName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println("exeName: " + exeName);
		
		//파일사이즈
		long fileSize = file.getSize();
		System.out.println("FileSize: " + fileSize);
		
		//저장파일명(겹치지 않아야 한다)
		String savaName = System.currentTimeMillis() + UUID.randomUUID().toString()+exeName;
		System.out.println("savaName: " + savaName);
		
		//파일 전체 경로+파일명
		String filePath = saveDir + "\\" + savaName;
		System.out.println("filePath: " + filePath);
		
		//(1)db저장
		//(1-1) 데이타 묶기
		AttachVo attachVo= new AttachVo(orgName, savaName, filePath, fileSize);
		System.out.println(attachVo);
		
        //(1-2) dao를 통해서 db에저장
		//과제.....
		System.out.println("과제:" + "db저장중.......");
		
		
		//(2)사진을 서버(강남)에 하드디스크에 복사해야된다
		//파일저장
		try {
			byte[] fileData = file.getBytes();
			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			bos.write(fileData);
			bos.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return savaName;  //시간+uuid+.jpg
	}

}
