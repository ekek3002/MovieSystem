package TPMovieSyeytem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InfoView {
	static Scanner sc = new Scanner(System.in);
	LoginDAO ldao = new LoginDAO();
	LoginInput lip = new LoginInput();
	Open open = new Open();
	MovieDAO mvdao = new MovieDAO();
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	boolean put = true;
	private static final String SELECT_NUM1 = "1";
	private static final String SELECT_NUM2 = "2";
	private static final String SELECT_NUM3 = "3";

	private static final String MEMBER_NUM1 = "1";
	private static final String MEMBER_NUM2 = "2";
	private static final String MEMBER_NUM3 = "3";
	private static final String MEMBER_NUM4 = "4";
	private static final String MEMBER_NUM5 = "5";
	private static final String MEMBER_NUM6 = "6";
	
	private static final String MOVIE_NUM1 = "1";
	private static final String MOVIE_NUM2 = "2";
	private static final String MOVIE_NUM3 = "3";
	private static final String MOVIE_NUM4 = "4";

	void mainLoop() {
		while (put) {
			showIntro();
			String choice = getSelect();
			switch (choice) {
			case SELECT_NUM1:
				System.out.println("로그인 화면 입니다.");
				int ret = lip.loginCheck();
				if(ret==0)break;
				else memberLoop();
				break;
			case SELECT_NUM2:
				System.out.println("회원가입 화면 입니다.");
				lip.loginInput();
				printInfo();
				break;
			case SELECT_NUM3:
				System.out.println();
				System.out.println("==============메가CGV시네마==============");
				System.out.println("============프로그램을 종료합니다============");
				System.exit(0);
				break;
			default:
				System.out.println("잘 못 입력하셨습니다. 다시 입력해주세요.");
				break;
			}
		}
	}

	void memberLoop() {
		while (put) {
			memberIntro();
			String choice = getSelect();
			switch (choice) {
			case MEMBER_NUM1:
				System.out.println("회원정보 조회 화면 입니다.");
				try {
					ldao.loginSearch(lip.member.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case MEMBER_NUM2:
				System.out.println("회원정보 수정 화면 입니다.");
				lip.updateInput(lip.member.getId());
				printInfo();
				break;
			case MEMBER_NUM3:
				System.out.println("회원탈퇴 화면 입니다.");
				try {
					ldao.deleteMember(lip.member.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case MEMBER_NUM4:
				System.out.println("상영 영화 조회 화면 입니다.");
				try {
					mvdao.moviePrint();
//					mvdao.ticketing();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case MEMBER_NUM5:
				System.out.println("상영 영화 검색 화면 입니다.");
				try {
					movieSearchLoop();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case MEMBER_NUM6:
				System.out.println("로그아웃");
				return;
			default:
				System.out.println("잘 못 입력하셨습니다. 다시 입력해주세요.");
				break;
			}
		}
	}
	void movieSearchLoop() throws SQLException {
		while (put) {
			movieSearchIntro();
			String choice = getSelect();
			switch (choice) {
			case MOVIE_NUM1:
				try {
					mvdao.movieSearch(choice);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case MOVIE_NUM2:
				try {
					mvdao.movieSearch(choice);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
				break;
			case MOVIE_NUM3:
				try {
					mvdao.movieSearch(choice);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case MOVIE_NUM4:
				System.out.println("이전 화면으로 돌아갑니다");
				return;
			default:
				System.out.println("잘 못 입력하셨습니다. 다시 입력해주세요.");
				break;
			}
		}
	}

	void showIntro() {
		System.out.println();
		System.out.println("==============메가CGV시네마=============");
		System.out.println("     1. 로그인   2. 회원가입  3. 종료");
		System.out.println("=====================================");
		System.out.println();
	}

	void memberIntro() {
		System.out.println();
		System.out.println("================================ " + lip.member.getId() + "님 반갑습니다" + " ==================================");
		System.out.println("   1. 회원정보 조회   2. 회원정보 수정  3. 회원탈퇴  4. 개봉 영화 조회  5. 개봉 영화 검색  6. 로그아웃   ");
		System.out.println("=============================================================================");
		System.out.println();
	}
	void movieSearchIntro() {
		System.out.println();
		System.out.println("================상영 영화 검색================");
		System.out.println("     1. 영화제목   2. 장르   3. 감독  4. 돌아가기");
		System.out.println("=========================================");
		System.out.println();
	}

	static String getSelect() {
		
		System.out.print("원하시는 메뉴의 숫자를 입력해주세요. : ");
		String choice = sc.nextLine();
//		while(true) {
//		if (choice.contains(" ")) {
//			System.out.println("공백은 입력이 불가합니다. 다시 입력해주세요. ");
//			continue;
//		}
		System.out.println();
		return choice;
//		}
	}

	void printInfo() {
		System.out.println("---아이디 	: " + lip.member.getId());
		System.out.println("---비밀번호 	: " + lip.member.getPw());
		System.out.println("---이름      	: " + lip.member.getName());
		System.out.println("---전화번호 	: " + lip.member.getPhone());
		System.out.println("---주소 		: " + lip.member.getAddress());
		System.out.println();
	}
}
