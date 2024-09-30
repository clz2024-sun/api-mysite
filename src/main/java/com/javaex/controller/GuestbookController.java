package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.GuestbookService;
import com.javaex.util.JsonResult;
import com.javaex.vo.GuestbookVo;

@RestController
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;

	//방명록 등록
	@PostMapping("/api/guestbooks")
	public JsonResult addGuestbook(@RequestBody GuestbookVo guestbookVo) {
		System.out.println("GuestbookController.addGuestbook()");
		System.out.println(guestbookVo);
		int count = guestbookService.exeAdd(guestbookVo);
		if(count == 1) {
			return JsonResult.success(count);
		}else {
			return JsonResult.fail("등록 실패");
		}
	}
	
	
	//방명록 전체
	@GetMapping("/api/guestbooks")
	public JsonResult getGuestbookList() {
		System.out.println("GuestbookController.getGuestbookList()");
		List<GuestbookVo> guestbookList = guestbookService.exeGuestbookList();
		
		if(guestbookList != null) {
			return JsonResult.success(guestbookList);
		}else {
			return JsonResult.fail("데이터 없음");
		}
	}
	
	//방명록 전체
	@DeleteMapping("/api/guestbooks/{no}")
	public JsonResult removeGuestbook(@PathVariable("no") int no,
								      @RequestBody GuestbookVo guestbookVo) {
		System.out.println("GuestbookController.removeGuestbook()");
		guestbookVo.setNo(no);
		System.out.println(guestbookVo);
		
		int count = guestbookService.exeRemove(guestbookVo);
		
		if(count == 1) {
			return JsonResult.success(count);
		}else {
			return JsonResult.fail("등록 실패");
		}
	}

}
