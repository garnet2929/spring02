package kr.itthis.exam.member.service;

import java.util.List;

import kr.itthis.exam.member.domain.Member;
import kr.itthis.exam.member.domain.MemberRegistCommand;

public interface MemberService {
	public boolean regist(MemberRegistCommand memberRegistCommand);
	public List<Member> listAll();
	public Member search(String email, String pwd);
	public boolean edit(MemberRegistCommand memberRegistCommand, String oldPwd);
	public boolean remove(String email, String pwd);
}

