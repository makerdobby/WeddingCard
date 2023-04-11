package com.thymeleaf.test.vo;

import lombok.Data;



@Data
public class ReplyVo {

	private int rId;
	private String rName;
	private String rPw;
	private String rMsg;
	private String rDt;
	private int gId;		// 참조하는 축하글의 ID

}
