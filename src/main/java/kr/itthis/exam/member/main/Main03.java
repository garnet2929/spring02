package kr.itthis.exam.member.main;

import java.util.List;

import kr.itthis.exam.member.domain.Member;
import kr.itthis.exam.member.domain.MemberRegistCommand;
import kr.itthis.exam.member.exception.ConfirmPasswordException;
import kr.itthis.exam.member.exception.EmailAlreadyExistsException;
import kr.itthis.exam.member.repository.MemberDaoImpl;
import kr.itthis.exam.member.service.MemberServiceImpl;

public class Main03 {
	public static void main(String[] args) {
		MemberServiceImpl msi = new MemberServiceImpl(new MemberDaoImpl());
		
		MemberRegistCommand memberCommand1 = new MemberRegistCommand("a@mail.com", "1234", "1234", "tester1");
		MemberRegistCommand memberCommand2 = new MemberRegistCommand("e@mail.com", "1234", "1", "tester2");
		MemberRegistCommand memberCommand3 = new MemberRegistCommand("e@mail.com", "1234", "1234", "tester3");
		
		try {
			System.out.println("등록:" + msi.regist(memberCommand1));
		}catch(EmailAlreadyExistsException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("등록:" + msi.regist(memberCommand2));
		}catch(ConfirmPasswordException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("등록:" + msi.regist(memberCommand3));
		}catch(ConfirmPasswordException e) {
			e.printStackTrace();
		}catch(EmailAlreadyExistsException e) {
			e.printStackTrace();
		}
		
		List<Member> ls = msi.listAll();
		
		for(Member member : ls) {
			System.out.println(member);
		}
	}
}
