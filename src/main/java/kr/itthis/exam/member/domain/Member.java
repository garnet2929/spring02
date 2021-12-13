package kr.itthis.exam.member.domain;

import java.time.LocalDate;

public class Member {
	private long seq;
	private String email;
	private String password;
	private String nickname;
	private LocalDate regdate;
	
	public Member() {}
	
	public Member(long seq, String email, String password, String nickname, LocalDate regdate) {
		super();
		this.seq = seq;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.regdate = regdate;
	}
	public long getSeq() {
		return seq;
	}
	public void setSeq(long seq) {
		this.seq = seq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public LocalDate getRegdate() {
		return regdate;
	}
	public void setRegdate(LocalDate regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "Member [seq=" + seq + ", email=" + email + ", password=" + password + ", nickname=" + nickname
				+ ", regdate=" + regdate + "]";
	}
	
	
}
