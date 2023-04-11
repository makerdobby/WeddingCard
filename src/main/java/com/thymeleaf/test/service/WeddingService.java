package com.thymeleaf.test.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thymeleaf.test.mapper.WeddingMapper;
import com.thymeleaf.test.vo.GuestVo;
import com.thymeleaf.test.vo.ReplyVo;

@Service
public class WeddingService {

	@Autowired
	WeddingMapper wMapper;


	public int getDatacount() {
		int dataCount = wMapper.select02();
		return dataCount;
	}

	public List<GuestVo> getVoList(Map<String, Integer> pageData) {
		List<GuestVo> guestList = wMapper.select01(pageData);
		return guestList;
	}
	public GuestVo getSingleVo(Integer gId) {
		GuestVo vo = wMapper.select03(gId);
		return vo;
	}

	public List<ReplyVo> getReplyList() {
		List<ReplyVo> replyList = wMapper.select04();
		return replyList;
	}
	public ReplyVo getSingleReply(Integer rId) {
		ReplyVo vo = wMapper.select05(rId);
		return vo;
	}

	public void insertVo(GuestVo gVo) {
		wMapper.insert01(gVo);
	}
	public void insertReply(ReplyVo rVo) {
		wMapper.insert02(rVo);
	}

	public void updateGuest(GuestVo gVo) {
		wMapper.update01(gVo);
	}
	public void updateReply(ReplyVo rVo) {
		wMapper.update02(rVo);
	}

	public void deleteGuest(int gId) {
		wMapper.delete01(gId);
		wMapper.delete02(gId);
	}

	public void deleteReply(int rId) {
		wMapper.delete03(rId);
	}

	public void simpleQuery() {
		wMapper.query();
	}


}

