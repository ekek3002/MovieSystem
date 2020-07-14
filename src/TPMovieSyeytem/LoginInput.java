package TPMovieSyeytem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LoginInput {
	LoginDAO ldao = new LoginDAO();
	HashMap<String, String> map = new HashMap<String, String>();
	Open open = new Open();
	Member member = new Member();
	ArrayList<Member> members = new ArrayList<Member>();
	Scanner sc = new Scanner(System.in);
	String id;
	String pw;
	String name;
	String phone;
	String address;

	void loginInput() {
		try {
			members = ldao.getMembers();
			for (int i = 0; i < members.size(); i++) {
				map.put(members.get(i).getId(), members.get(i).getPw());
			}

			while (true) {
				BlankId();
				
				if (map.containsKey(member.getId())) {
					System.out.println("입력하신 id는 이미 존재합니다. 다시 입력해주세요.");
					continue;
				}

				BlankPw();
				BlankName();
				BlankPhone();
				BlankAddress();
				ldao.insertMember(member);
				break;
			}
			
		} catch (SQLException e) {
			System.out.println("예외처리 :" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("예외처리 6:" + e.getMessage());
			e.printStackTrace();
		}

	}

	void updateInput(String id) {

		try {		
			BlankPw();
			BlankName();
			BlankPhone();
			BlankAddress();
			ldao.updataMember(member);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	int loginCheck() {

		try {
			members = ldao.getMembers();
			for (int i = 0; i < members.size(); i++) {
				map.put(members.get(i).getId(), members.get(i).getPw());
			}

			while (true) {
				BlankId();
				BlankPw();
				System.out.println();

				if (!map.containsKey(member.getId())) {
					System.out.println("입력하신 id는 존재하지 않습니다. 다시 입력해주세요.");

				} else {
					if (!(map.get(id)).equals(member.getPw())) {
						System.out.println("입력하신 비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
					} else {
						System.out.println("로그인 되었습니다");
						member.setId(id);
						break;
					}
				}
				System.out.print("계속 진행하시겠습니까? (돌아가기 Y 입력/ 계속 진행하시려면 아무 키나 입력해 주세요.) : ");

				String yn = sc.nextLine();
				if (yn.equalsIgnoreCase("Y")) {
					System.out.println("이전 화면으로 이동합니다.");
					return 0;
				}
			}
		} catch (Exception e) {
			System.out.println("예외처리 6:" + e.getMessage());
			e.printStackTrace();
		}
		return 1;
	}

	void BlankId() {
		while (true) {
			System.out.print("아이디를 입력해주세요: ");
			member.setId(id=sc.nextLine());
			if (id.isEmpty()||id.contains(" ")) {
				System.out.println("다시 입력해주세요. ");
				continue;
			}
			break;
		}
	}

	void BlankPw() {
		while (true) {
			System.out.print("비밀번호를 입력해주세요: ");
			member.setPw(pw = sc.nextLine());
			if (pw.isEmpty() || pw.contains(" ")) {
				System.out.println("다시 입력해주세요. ");
				continue;
			}
			break;
		}
	}

	void BlankName() {
		while (true) {
			System.out.print("이름을 입력해주세요: ");
			member.setName(name = sc.nextLine());
			if (name.isEmpty() || name.contains(" ")) {
				System.out.println("다시 입력해주세요. ");
				continue;
			}
			break;
		}
	}

	void BlankPhone() {
		while (true) {
			System.out.print("전화번호를 입력해주세요: ");
			member.setPhone(phone = sc.nextLine());
			if (phone.isEmpty() ||phone.contains(" ")) {
				System.out.println("다시 입력해주세요. ");
				continue;
			}
			break;
		}
	}

	void BlankAddress() {
		while (true) {
			System.out.print("주소를 입력해주세요: ");
			member.setAddress(address = sc.nextLine());
			if (address==null||address.isEmpty()) {
				System.out.println("다시 입력해주세요. ");
				continue;
			}
			break;
		}
	}
}
