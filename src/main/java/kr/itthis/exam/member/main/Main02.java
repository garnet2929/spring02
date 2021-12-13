package kr.itthis.exam.member.main;

import kr.itthis.exam.member.domain.Member;
import kr.itthis.exam.member.exception.MemberExistsException;
import kr.itthis.exam.member.exception.PasswordAuthException;
import kr.itthis.exam.member.repository.MemberDaoImpl;
import kr.itthis.exam.member.service.MemberServiceImpl;

public class Main02 {
	public static void main(String[] args) {
		MemberServiceImpl msi = new MemberServiceImpl(new MemberDaoImpl());
		
		Member member = msi.search("b@mail.com", "1234");
		System.out.println(member);
		
		try {
			System.out.println(msi.search("b@mail.com", "4321"));
		}catch(PasswordAuthException e) {
			System.out.println("비밀번호가 맞지 않음");
			e.printStackTrace();
		}
		try {
			System.out.println(msi.search("d@mail.com", "4321"));
		}catch(MemberExistsException e) {
			System.out.println("멤버를 찾을 수 없음");
			e.printStackTrace();
		}
	}
}
