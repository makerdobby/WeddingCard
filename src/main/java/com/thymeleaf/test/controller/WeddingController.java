package com.thymeleaf.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.thymeleaf.test.service.WeddingService;
import com.thymeleaf.test.vo.GuestVo;
import com.thymeleaf.test.vo.ReplyVo;

@Controller
public class WeddingController {

	@Autowired
	WeddingService wService;

	@SuppressWarnings("unused")
	private final int PAGE_SIZE = 5;

	// 최초 진입화면
	@GetMapping("/")
	public ModelAndView weddingMain(ModelAndView mv) {
		System.out.println("weddingMain 진입");
		mv.setViewName("home/index");
		return mv;
	}

	// board voList ajax 조회
	@GetMapping("/wedding/ajax")
	public ModelAndView weddingAjax( ModelAndView mv, @RequestParam(required = false, defaultValue = "1") Integer curPage) {
		System.out.println("weddingAjax 진입");
		System.out.println(curPage);
		int PAGE_SIZE = 5;
		int startRow = (curPage-1)*PAGE_SIZE + 1;
		int endRow = startRow + (PAGE_SIZE-1);

		// DB에 넣을 파라미터 Map 만들기
		Map<String, Integer> pageData = new HashMap<>();
		pageData.put("startRow", startRow);
		pageData.put("endRow", endRow);

		List<GuestVo> voList = wService.getVoList(pageData);

		// 날짜 보기 좋게 바꾸기
		for(GuestVo vo : voList) {
			vo.setGDt( vo.getGDt().substring(0,16) );
		}
		mv.addObject("voList", voList);

		// 전체 페이지
		int dataCount = wService.getDatacount();
		int lastPage = (int) Math.ceil((double)dataCount/(double)PAGE_SIZE);
		mv.addObject("lastPage",lastPage);

		// 댓글 전체 조회
		List<ReplyVo> rVoList = wService.getReplyList();
		mv.addObject("rVoList", rVoList);

		mv.setViewName("wedding/ajax");
		return mv;
	}

	// 삽입
	@PostMapping("/guest/insert")
	public ModelAndView insertGuest(ModelAndView mv, HttpSession session, GuestVo gVo) {
		System.out.println("insertGuest 진입");

		// session에 이름과 비밀번호 저장
		session.setAttribute("sessionName", gVo.getGName());
		session.setAttribute("sessionPw", gVo.getGPw());

		wService.insertVo(gVo);

		mv.setViewName("redirect:/");

		return mv;
	}
	// 삽입
	@PostMapping("/reply/insert")
	public ModelAndView insertReply(ModelAndView mv, HttpSession session, ReplyVo rVo) {
		System.out.println("insertReply 진입");

		// session에 이름과 비밀번호 저장
		session.setAttribute("sessionName", rVo.getRName());
		session.setAttribute("sessionPw", rVo.getRPw());

		wService.insertReply(rVo);

		mv.setViewName("redirect:/");

		return mv;
	}

	// 편집 페이지
	@GetMapping("/guest/edit")
	public ModelAndView goEditGuest(Integer gId, ModelAndView mv) {
		System.out.println("goEditGuest 진입");

		GuestVo vo = wService.getSingleVo(gId);

		// 날짜 보기 좋게 바꾸기
		vo.setGDt( vo.getGDt().substring(0,16) );

		mv.addObject("vo",vo);
		mv.setViewName("wedding/edit");
		return mv;
	}

	// 댓글 편집 페이지
	@GetMapping("/reply/edit")
	public ModelAndView goEditReply(Integer rId, ModelAndView mv) {
		System.out.println("goEditReply 진입");

		ReplyVo vo = wService.getSingleReply(rId);

		// 날짜 보기 좋게 바꾸기
		vo.setRDt( vo.getRDt().substring(0,16) );

		mv.addObject("vo",vo);
		mv.setViewName("wedding/edit2");
		return mv;
	}

	// 수정 실행
	@PostMapping("/guest/update")
	public ModelAndView updateGuest(ModelAndView mv, HttpSession session, GuestVo gVo) {
		System.out.println("updateGuest 진입");

		// session에 이름과 비밀번호 저장
		session.setAttribute("sessionName", gVo.getGName());
		session.setAttribute("sessionPw", gVo.getGPw());


		wService.updateGuest(gVo);

		mv.setViewName("redirect:/");
		return mv;
	}

	// 댓글 수정 실행
	@PostMapping("/reply/update")
	public ModelAndView updateReply(ModelAndView mv, HttpSession session, ReplyVo rVo) {
		System.out.println("updateReply 진입");

		// session에 이름과 비밀번호 저장
		session.setAttribute("sessionName", rVo.getRName());
		session.setAttribute("sessionPw", rVo.getRPw());


		wService.updateReply(rVo);

		mv.setViewName("redirect:/");
		return mv;
	}

	// 삭제 실행
	@PostMapping("/guest/delete")
	public ModelAndView deleteGuest(ModelAndView mv, GuestVo gVo) {
		System.out.println("deleteGuest 진입");

		int gId = gVo.getGId();
		wService.deleteGuest(gId);

		mv.setViewName("redirect:/");
		return mv;
	}

	// 댓글 삭제 실행
	@PostMapping("/reply/delete")
	public ModelAndView deleteReply(ModelAndView mv, ReplyVo rVo) {
		System.out.println("deleteReply 진입");

		int rId = rVo.getRId();
		wService.deleteReply(rId);

		mv.setViewName("redirect:/");
		return mv;
	}

	// 세션 닫혔을 때 실행하는 간단한 쿼리
	@GetMapping("/test")
	public String testQuery() {
		wService.simpleQuery();
		return "redirect:/";
	}






}
