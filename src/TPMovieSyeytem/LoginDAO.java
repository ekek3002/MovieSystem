package TPMovieSyeytem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LoginDAO {
	Scanner sc = new Scanner(System.in);
	Open open = new Open();
	Close close = new Close();
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;

	public boolean insertMember(Member member) throws Exception {
		boolean ok = false;

		try {

			con = open.getConnection();
			sql = "INSERT INTO MEMBER(" + "id,password,name,mobile,address) " + "values(?,?,?,?,?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getAddress());
			int r = pstmt.executeUpdate();

			if (r > 0) {
				System.out.println("회원가입이 완료 되었습니다.");
				ok = true;
			} else {
				System.out.println("회원가입에 실패 하셨습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close.execClose(null, pstmt, con);
		}

		return ok;
	}

	public boolean updataMember(Member member) throws Exception {
		boolean ok = false;
		 
		try {
			con = open.getConnection();
			con.setAutoCommit(false);
			sql = "update MEMBER set password=?, name=?, mobile=?, address=? where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getPw());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getId());

			
			int r = pstmt.executeUpdate();
			if (r > 0) {
				System.out.println("회원정보 변경이 완료 되었습니다.");
				ok = true;
			} else {
				System.out.println("회원정보 변경에 실패 하셨습니다.");
			}
			 con.setAutoCommit(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		} finally {
			close.execClose(null, pstmt, con);
		}
		return ok;
	}

		

	public void loginSearch(String id) throws Exception {
		ArrayList<Member> members = new ArrayList<Member>();
		Member member = null;
		try {
			con = open.getConnection();
			sql = "select * from MEMBER where id= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new Member();
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("mobile"));
				member.setAddress(rs.getString("address"));
				members.add(member);
				printInfo(member);
			}

		} catch (SQLException e) {
			System.out.println("예외처리 :" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("예외처리 :" + e.getMessage());
			e.printStackTrace();
		} finally {
			close.execClose(rs, pstmt, con);
		}
	}

	public void deleteMember(String id) throws Exception {
		try {
			con = open.getConnection();

			sql = "delete from MEMBER where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			while (true) {
				System.out.print("정말 회원탈퇴를 하시겠습니까? (Y / N) : ");
				String yn=sc.nextLine();
				if (yn.equalsIgnoreCase("Y")) {
					pstmt.executeUpdate();
					System.out.println("회원탈퇴가 완료되었습니다. 프로그램을 종료합니다.");
					System.exit(0);
					break;
				} else if (yn.equalsIgnoreCase("N")) {
					System.out.println("회원탈퇴가 취소되었습니다. 이전 화면으로 이동합니다.");
					break;
				} else {
					System.out.println("잘못 입력하셨습니다.");
					System.out.println();
				}	
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close.execClose(null, pstmt, con);
		}
	}
	public ArrayList<Member> getMembers() throws Exception {
		
		ArrayList<Member> members= new ArrayList<Member>();
		Member member;
		
		con = open.getConnection();
		String sql = "SELECT * FROM MEMBER";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member= new Member();
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("mobile"));
				member.setAddress(rs.getString("address"));	
				members.add(member);
			}
		} catch (SQLException e) {
			System.out.println("예외처리 3:"+e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("예외처리 4:"+e.getMessage());
			e.printStackTrace();
		}finally {
			close.execClose(rs, pstmt, con);
		}
		return members;
	}
	void printInfo(Member member) {
		System.out.println("---아이디 		: " + member.getId());
		System.out.println("---비밀번호 	: " + member.getPw());
		System.out.println("---이름      	: " + member.getName());
		System.out.println("---전화번호 	: " + member.getPhone());
		System.out.println("---주소 		: " + member.getAddress());
		System.out.println();
	}    
}
