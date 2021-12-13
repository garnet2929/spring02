package kr.itthis.exam.member.service;

import java.util.List;

import kr.itthis.exam.member.commons.ResultStateValues;
import kr.itthis.exam.member.domain.Member;
import kr.itthis.exam.member.domain.MemberRegistCommand;
import kr.itthis.exam.member.exception.ConfirmPasswordException;
import kr.itthis.exam.member.exception.EmailAlreadyExistsException;
import kr.itthis.exam.member.exception.MemberExistsException;
import kr.itthis.exam.member.exception.PasswordAuthException;
import kr.itthis.exam.member.repository.MemberDao;

public class MemberServiceImpl implements MemberService{
	
	private MemberDao memberDao;
	
	public MemberServiceImpl(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Override
	public boolean regist(MemberRegistCommand memberRegistCommand) {
		Member member = memberDao.selectOne(memberRegistCommand.getEmail());
		int result = -1;
		if(member != null) {
			throw new EmailAlreadyExistsException();
		}
		if(!memberRegistCommand.isPasswordAndConfirmPasswordSame()) {
			throw new ConfirmPasswordException();
		}
		
		member = new Member();
		member.setEmail(memberRegistCommand.getEmail());
		member.setPassword(memberRegistCommand.getPassword());
		member.setNickname(memberRegistCommand.getNickname());
		result = memberDao.insert(member);
		return result == ResultStateValues.SUCCESS ? true : false;
	}

	@Override
	public List<Member> listAll() {
		return memberDao.selectList();
	}

	@Override
	public Member search(String email, String pwd) {
		Member member = memberDao.selectOne(email);
		if(member == null) {
			throw new MemberExistsException();
		}
		if(!member.getPassword().equals(pwd)) {
			throw new PasswordAuthException();
		}
		return memberDao.selectOne(email);
	}

	@Override
	public boolean edit(MemberRegistCommand memberRegistCommand, String oldPwd) {
		Member member = memberDao.selectOne(memberRegistCommand.getEmail());
		if(member == null) {
			throw new MemberExistsException();
		}
		if(!memberRegistCommand.isPasswordAndConfirmPasswordSame()) {
			throw new ConfirmPasswordException();
		}
		if(!member.getPassword().equals(oldPwd)) {
			throw new PasswordAuthException();
		}
		member.setNickname(memberRegistCommand.getNickname());
		member.setPassword(memberRegistCommand.getPassword());
		return memberDao.update(member) == ResultStateValues.SUCCESS ? true : false;
	}

	@Override
	public boolean remove(String email, String pwd) {
		Member member = memberDao.selectOne(email);
		if(member == null) {
			throw new MemberExistsException();
		}
		if(!member.getPassword().equals(pwd)) {
			throw new PasswordAuthException();
		}
		return memberDao.delete(email) == ResultStateValues.SUCCESS ? true : false;
	}
}
