package kr.itthis.exam.member.repository;

import java.util.List;

import kr.itthis.exam.member.domain.Member;

public interface MemberDao {
	public int insert(Member member);
	public List<Member> selectList();
	public Member selectOne(String email);
	public int update(Member member);
	public int delete(String email);
}


