package kr.itthis.exam.member.main;

import java.util.List;

import kr.itthis.exam.member.domain.Member;
import kr.itthis.exam.member.domain.MemberRegistCommand;
import kr.itthis.exam.member.exception.ConfirmPasswordException;
import kr.itthis.exam.member.exception.EmailAlreadyExistsException;
import kr.itthis.exam.member.exception.MemberExistsException;
import kr.itthis.exam.member.repository.MemberDaoImpl;
import kr.itthis.exam.member.service.MemberServiceImpl;

public class Main04 {
	public static void main(String[] args) {
		MemberServiceImpl msi = new MemberServiceImpl(new MemberDaoImpl());
		
		MemberRegistCommand memberCommand1 = new MemberRegistCommand("aa@mail.com", "1111", "1111", "tester1수정");
		MemberRegistCommand memberCommand2 = new MemberRegistCommand("a@mail.com", "1111", "12", "tester1수정");
		MemberRegistCommand memberCommand3 = new MemberRegistCommand("a@mail.com", "1111", "1111", "tester1수정");
		
		List<Member> ls = msi.listAll();
		
		for(Member member : ls) {
			System.out.println(member);
		}
		
		try {
			System.out.println("수정:" + msi.edit(memberCommand1, "1111"));
		}catch(MemberExistsException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("수정:" + msi.edit(memberCommand2, "1111"));
		}catch(ConfirmPasswordException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("수정:" + msi.edit(memberCommand3, "1234"));
		}catch(ConfirmPasswordException e) {
			e.printStackTrace();
		}catch(EmailAlreadyExistsException e) {
			e.printStackTrace();
		}
		
		ls = msi.listAll();
		
		for(Member member : ls) {
			System.out.println(member);
		}
	}
}
