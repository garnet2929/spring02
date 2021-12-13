package kr.itthis.exam.member.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.itthis.exam.member.commons.ResultStateValues;
import kr.itthis.exam.member.domain.Member;

public class MemberDaoImpl implements MemberDao{
	
	private static Map<String, Member> db = new HashMap<>();
	private static long sequence = 1;
	
	static {
		db.put("a@mail.com", new Member(sequence++, "a@mail.com", "1234", "testa", LocalDate.now()));
		db.put("b@mail.com", new Member(sequence++, "b@mail.com", "1234", "testb", LocalDate.now()));
		db.put("c@mail.com", new Member(sequence++, "c@mail.com", "1234", "testc", LocalDate.now()));
	}

	@Override
	public int insert(Member member) {
		member.setSeq(sequence++);
		member.setRegdate(LocalDate.now());
		Member result = db.put(member.getEmail(), member);
		return result == null ? ResultStateValues.SUCCESS : ResultStateValues.FAIL;
	}

	@Override
	public List<Member> selectList() {
		return new ArrayList<Member>(db.values());
	}

	@Override
	public Member selectOne(String email) {
		return db.get(email);
	}

	@Override
	public int update(Member member) {
		Member result = db.put(member.getEmail(), member);
		return result != null ? ResultStateValues.SUCCESS : ResultStateValues.FAIL;
	}

	@Override
	public int delete(String email) {
		Member result = db.remove(email);
		return result != null ? ResultStateValues.SUCCESS : ResultStateValues.FAIL;
	}

}
