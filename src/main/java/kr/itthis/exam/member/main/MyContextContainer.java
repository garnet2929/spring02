package kr.itthis.exam.member.main;

import kr.itthis.exam.member.repository.MemberDao;
import kr.itthis.exam.member.repository.MemberDaoImpl;
import kr.itthis.exam.member.service.MemberService;
import kr.itthis.exam.member.service.MemberServiceImpl;

public class MyContextContainer {
	private MemberDao memberDao;
	private MemberService memberService;
	
	public MyContextContainer() {
		memberDao = new MemberDaoImpl();
		memberService = new MemberServiceImpl(memberDao);
	}
	
	public MemberDao getMemberDao() {
		return memberDao;
	}
	
	public MemberService getMemberService() {
		return memberService;
	}
}
