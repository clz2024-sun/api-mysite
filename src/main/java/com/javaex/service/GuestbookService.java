package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao; //no비어있음;

	// 등록폼+리스트
	public List<GuestbookVo> exeGuestbookList() {
		System.out.println("GuestbookService.exeGuestbookList()");

		List<GuestbookVo> guestbookList = guestbookDao.guestbookSelectList();

		return guestbookList;
	}

	// 등록
	public int exeAdd(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService.exeAdd()");

		int count = guestbookDao.guestbookInsert(guestbookVo);
		return count;
	}

	// 삭제
	public int exeRemove(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService.exeRemove()");

		int count = guestbookDao.guestbookDelete(guestbookVo);
		return count;
	}

	//ajax등록  저장
	public GuestbookVo exeAddandGuest(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService.exeAddandGuest()");
		//*비지니스 로직///////////////////////////////////////////
		//저장
		int count = guestbookDao.insertSelectKey(guestbookVo);
		
		//1명데이터 데이터 가져오기
		GuestbookVo gVo = guestbookDao.guestbookSelectOne(guestbookVo.getNo());
		/////////////////////////////////////////////////////////////
		
		return gVo;
	}
	

}