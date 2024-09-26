package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	/* 로그인 */
	@PostMapping("api/users/login")
	public JsonResult login(@RequestBody UserVo userVo, 
			          HttpServletResponse response) {
		System.out.println("UserController.login()");
	
		UserVo authUser = userService.exeLogin(userVo);
		if(authUser != null ) {//로그인 성공
			//토큰을 만들고 "응답문서의 해더"에 토큰을 붙여서 보낸다
			JwtUtil.createTokenAndSetHeader(response, ""+authUser.getNo());
			return JsonResult.success(authUser);
		}else {
			return JsonResult.fail("로그인실패");
		}
	}
	
	/* 회원정보수정폼(3번회원정보가져오기) */
	@GetMapping("/api/users/me")
	public JsonResult editForm(HttpServletRequest request) {
		System.out.println("UserController.edifForm()");
		
		//요청해더에서 토큰을 꺼내서 유효성을 체크한후 정상이면 no값을 꺼내준다
		int no = JwtUtil.getNoFromHeader(request);
		System.out.println(no);
		
		if(no != -1) {  //토큰정상
			UserVo userVo = userService.exeEditForm(no);
			return JsonResult.success(userVo);
		
		}else {//토큰비정상
			return JsonResult.fail("토큰X, 비로그인, 변조");
		}
		
	}
	
}
