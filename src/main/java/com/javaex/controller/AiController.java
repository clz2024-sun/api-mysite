package com.javaex.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.util.JsonResult;

@RestController
public class AiController {

	@PostMapping("/api/ai/chats")
	public JsonResult chat(@RequestParam(value="question") String question) {
		System.out.println("AiController.chat()");
		System.out.println(question);
		
		String answer = "";
		
		
		try {
			ProcessBuilder processBuilder = new ProcessBuilder(
					"C:\\javaStudy\\workspace-python\\Ex05\\ex05_venv\\Scripts\\python.exe", 
					"C:\\javaStudy\\workspace-python\\Ex05\\main.py", 
					question
			);
			
			//파이썬에서 전달하는 메세지, 파이썬에서 발생하는 에러메세지 따로 관리된다.
			//이것을 1개로 관리할 수있다
			processBuilder.redirectErrorStream(true); 
			
			//파이썬 스트립트실행
			Process process = processBuilder.start();
			
			//////////////////////////////////
			//대답 받기
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			
			while(true) {
				String line = br.readLine();
				if(line == null) {
					break;
					
				}else  {
					answer += line+"<br/>";
				}
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(answer);
		return JsonResult.success(answer);
		
	}
	
}
