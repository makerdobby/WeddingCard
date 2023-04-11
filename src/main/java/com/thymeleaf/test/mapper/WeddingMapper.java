package com.thymeleaf.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.thymeleaf.test.vo.GuestVo;
import com.thymeleaf.test.vo.ReplyVo;

@Mapper
public interface WeddingMapper {

	public int select02();

	public List<GuestVo> select01( Map<String, Integer> mappageData);
	public GuestVo select03(Integer gId);

	public List<ReplyVo> select04();
	public ReplyVo select05(Integer rId);

	public void insert01(GuestVo gVo);
	public void insert02(ReplyVo rVo);

	public void update01(GuestVo gVo);
	public void update02(ReplyVo rVo);

	public void delete01(int gId);
	public void delete02(int gId);
	public void delete03(int rId);

	public void query();



}
