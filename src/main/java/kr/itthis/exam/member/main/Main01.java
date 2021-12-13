package kr.itthis.exam.member.main;

import java.util.List;

import kr.itthis.exam.member.domain.Member;
import kr.itthis.exam.member.repository.MemberDaoImpl;
import kr.itthis.exam.member.service.MemberServiceImpl;

public class Main01 {
	public static void main(String[] args) {
		MemberServiceImpl msi = new MemberServiceImpl(new MemberDaoImpl());
		
		List<Member> ls = msi.listAll();
		
		for(Member member : ls) {
			System.out.println(member);
		}
	}
}
