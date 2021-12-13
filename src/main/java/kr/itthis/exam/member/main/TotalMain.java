package kr.itthis.exam.member.main;

import java.util.List;
import java.util.Scanner;

import kr.itthis.exam.member.domain.Member;
import kr.itthis.exam.member.domain.MemberRegistCommand;
import kr.itthis.exam.member.exception.ConfirmPasswordException;
import kr.itthis.exam.member.exception.EmailAlreadyExistsException;
import kr.itthis.exam.member.exception.MemberExistsException;
import kr.itthis.exam.member.exception.PasswordAuthException;
import kr.itthis.exam.member.repository.MemberDaoImpl;
import kr.itthis.exam.member.service.MemberServiceImpl;

public class TotalMain {
	
	private static MemberServiceImpl msi = new MemberServiceImpl(new MemberDaoImpl());
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		while(true) {
			String command = "";
			System.out.print("명령어 입력>");
			command = in.nextLine();
			if(command.startsWith("list")) {
				listProcess();
			}else if(command.startsWith("search")) {
				searchProcess(command.split(" "));
			}else if(command.startsWith("regist")) {
				registProcess(command.split(" "));
			}else if(command.startsWith("edit")) {
				editProcess(command.split(" "));
			}else if(command.startsWith("remove")) {
				removeProcess(command.split(" "));
			}else if(command.equals("quit")){
				System.out.println("프로그램 종료!");
				return;
			}else {
				printHelp();
			}
		}
	}
	
	private static void listProcess() {
		List<Member> ls = msi.listAll();
		for(Member member : ls) {
			System.out.println(member);
		}
		System.out.println("목록 출력 완료.");
	}
	
	private static void searchProcess(String[] args) {
		if(args.length != 3) {
			return;
		}
		try {
			System.out.println(msi.search(args[1], args[2]));
			System.out.println("수정 성공.");
		}catch(PasswordAuthException e) {
			System.out.println("비밀번호가 맞지 않음");
			//e.printStackTrace();
		}catch(MemberExistsException e) {
			System.out.println("멤버를 찾을 수 없음");
			//e.printStackTrace();
		}
	}
	
	private static void registProcess(String[] args) {
		if(args.length != 5) {
			return;
		}
		MemberRegistCommand memberCommand = 
				new MemberRegistCommand(args[1], args[2], args[3], args[4]);
		try {
			msi.regist(memberCommand);
			System.out.println("등록 성공");
		}catch(ConfirmPasswordException e) {
			System.out.println("비밀번호 확인.");
			//e.printStackTrace();
		}catch(EmailAlreadyExistsException e) {
			System.out.println("존재하는 이메일 아이디.");
			//e.printStackTrace();
		}
		
	}

	private static void editProcess(String[] args) {
		if(args.length != 6) {
			return;
		}
		MemberRegistCommand memberCommand = 
				new MemberRegistCommand(args[1], args[2], args[3], args[4]);
		try {
			msi.edit(memberCommand, args[5]);
			System.out.println("수정 성공");
		}catch(PasswordAuthException e) {
			System.out.println("기존 비밀번호 확인.");
			//e.printStackTrace();
		}catch(ConfirmPasswordException e) {
			System.out.println("새 비밀번호 확인.");
			//e.printStackTrace();
		}catch(MemberExistsException e) {
			System.out.println("멤버가 존재하지 않음.");
			//e.printStackTrace();
		}
	}

	private static void removeProcess(String[] args) {
		if(args.length != 3) {
			return;
		}
		try {
			msi.remove(args[1], args[2]);
			System.out.println("삭제 성공.");
		}catch(PasswordAuthException e) {
			System.out.println("비밀번호가 맞지 않음");
			//e.printStackTrace();
		}catch(MemberExistsException e) {
			System.out.println("멤버를 찾을 수 없음");
			//e.printStackTrace();
		}
	}
	
	private static void printHelp() {
		System.out.println("Usage:");
		System.out.println("regist <Email> <Password1> <Password2> <Nickname>");
		System.out.println("edit <Email> <NewPassword1> <NewPassword2> <Nickname> <OldPassword>");
		System.out.println("search <Email> <Password>");
		System.out.println("list");
		System.out.println("remove <Email> <Password>");
		System.out.println("quit");
	}
}
