package TPMovieSyeytem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MovieDAO {
	Scanner sc = new Scanner(System.in);
	Open open = new Open();
	Close close = new Close();
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;

	public void moviePrint() throws Exception {
		con = open.getConnection();
		String sql = "SELECT m.movie_number,genre,running_time,director,title,screening_date,total_seats "
				+ "FROM MOVIE m, MOVIE_SCENE ms, SCREEN s WHERE m.movie_number = ms.movie_number "
				+ "AND ms.screen_number = s.screen_number ORDER BY m.movie_number ";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println(
					"=========================================================================================================================");
			System.out.printf("|%-6s\t|", "영화번호");
			System.out.printf("%-30s\t|", "제목");
			System.out.printf("%-20s\t|", "장르");
			System.out.printf("%-50s\t|", "감독");
			System.out.printf("%-20s|", "상영 날짜");
			System.out.printf("%-30s|", "상영 시간");
			System.out.printf("%-5s\t|\n", "총 좌석수");
			System.out.println(
					"=========================================================================================================================");

			while (rs.next()) {
				System.out.println(
						"-------------------------------------------------------------------------------------------------------------------------");
				System.out.printf("|%-6s\t|%-30s\t|%-20s\t|%-50s\t|%-13s|%-20s\t|%-5s\t|\n",
						rs.getString("movie_number"), rs.getString("title"), rs.getString("genre"),
						rs.getString("director"), rs.getString("screening_date"), rs.getString("running_time"),
						rs.getString("total_seats"));
				System.out.println();
			}
			System.out.println(
					"=========================================================================================================================");
			System.out.println();
			System.out.println("조회가 완료되었습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close.execClose(rs, pstmt, con);
		}
	}

	public void movieSearch(String choice) throws Exception {

		con = open.getConnection();
		try {
			while (true) {
				if (choice.equals("1")) {
					System.out.println("영화 제목으로 검색합니다.");
					System.out.print("영화 제목을 입력해주세요 : ");
					String title = sc.next();
					String sql1 = "SELECT m.movie_number,genre,running_time,director,title,screening_date,total_seats "
							+ "FROM MOVIE m, MOVIE_SCENE ms, SCREEN s WHERE m.movie_number = ms.movie_number "
							+ "AND ms.screen_number = s.screen_number AND title LIKE '%'||?||'%' ORDER BY title";
					pstmt = con.prepareStatement(sql1, rs.TYPE_SCROLL_SENSITIVE, rs.CONCUR_UPDATABLE);
					pstmt.setString(1, title);
				}
				if (choice.equals("2")) {
					System.out.println("영화 장르로 검색합니다.");
					System.out.print("영화 장르를 입력해주세요 : ");
					String genre = sc.next();
					String sql2 = "SELECT m.movie_number,genre,running_time,director,title,screening_date,total_seats "
							+ "FROM MOVIE m, MOVIE_SCENE ms, SCREEN s WHERE m.movie_number = ms.movie_number "
							+ "AND ms.screen_number = s.screen_number AND genre LIKE '%'||?||'%' ORDER BY genre";
					pstmt = con.prepareStatement(sql2, rs.TYPE_SCROLL_SENSITIVE, rs.CONCUR_UPDATABLE);
					pstmt.setString(1, genre);
				}
				if (choice.equals("3")) {
					System.out.println("감독 이름으로 검색합니다.");
					System.out.print("감독 이름을 입력해주세요 : ");
					String director = sc.next();
					String sql3 = "SELECT m.movie_number,genre,running_time,director,title,screening_date,total_seats "
							+ "FROM MOVIE m, MOVIE_SCENE ms, SCREEN s WHERE m.movie_number = ms.movie_number "
							+ "AND ms.screen_number = s.screen_number AND director LIKE '%'||?||'%' ORDER BY director";
					pstmt = con.prepareStatement(sql3, rs.TYPE_SCROLL_SENSITIVE, rs.CONCUR_UPDATABLE);
					pstmt.setString(1, director);
				}
				rs = pstmt.executeQuery();
				if (rs.next()) {
					System.out.println(
							"=========================================================================================================================");
					System.out.printf("|%-6s\t|", "영화번호");
					System.out.printf("%-30s\t|", "제목");
					System.out.printf("%-20s\t|", "장르");
					System.out.printf("%-50s\t|", "감독");
					System.out.printf("%-20s|", "상영 날짜");
					System.out.printf("%-30s|", "상영 시간");
					System.out.printf("%-5s\t|\n", "총 좌석수");
					rs.beforeFirst();
					while (rs.next()) {

						System.out.println(
								"-------------------------------------------------------------------------------------------------------------------------");
						System.out.printf("|%-6s\t|%-30s\t|%-20s\t|%-50s\t|%-13s|%-20s\t|%-5s\t|\n",
								rs.getString("movie_number"), rs.getString("title"), rs.getString("genre"),
								rs.getString("director"), rs.getString("screening_date"), rs.getString("running_time"),
								rs.getString("total_seats"));

					}
				} else {
					System.out.println("잘 못 입력하셨습니다.");
					System.out.println("계속 진행하시겠습니까? (돌아가기 Y 입력/ 계속 진행하시려면 아무 키나 입력해 주세요.) : ");
					String yn = sc.next().trim();
					if (yn.equalsIgnoreCase("Y")) {
						break;
					} else {
						continue;
					}
				}
				System.out.println(
						"=========================================================================================================================");
				System.out.println();
				System.out.println("검색이 완료되었습니다.");
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			close.execClose(rs, pstmt, con);
		}
	}
	
	
//	public void seatPrint() throws Exception {
//	con = open.getConnection();
//	String sql = "SELECT * FROM SEAT ";
//	try {
//		pstmt = con.prepareStatement(sql);
//		rs = pstmt.executeQuery();
//		System.out.println("=============================================================");
//		
//		while (rs.next()) {				
//			System.out.println("-------------------------------------------------------------");
//			System.out.printf("|%-3s\t|%-3s\t|%-3s\t|%-3s\t|%-3s|\n",rs.getString("A1"),rs.getString("A2"),rs.getString("A3"),rs.getString("A4"),rs.getString("A5"));
//			System.out.println();
//		}
//		System.out.println("==============================================================");
//		System.out.println();
//		System.out.println("조회가 완료되었습니다.");
//		
//		
//	} catch (Exception e) {
//		e.printStackTrace();
//	} finally {
//		close.execClose(rs, pstmt, con);
//	}
//}
//	public void ticketing() throws Exception {
//		try {
//			seatPrint();
//			System.out.println("원하시는 좌석을 입력해주세요");
//			con = open.getConnection();
//			String seat=sc.next().trim();
//			String sql = "SELECT * FROM SEAT WHERE A1=? OR A2=? OR A3=? OR A4=? OR A5=? ";
//			pstmt = con.prepareStatement(sql, rs.TYPE_SCROLL_SENSITIVE, rs.CONCUR_UPDATABLE);
//			//pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, seat);
//			pstmt.setString(2, seat);
//			pstmt.setString(3, seat);
//			pstmt.setString(4, seat);
//			pstmt.setString(5, seat);
//			rs = pstmt.executeQuery();
//			System.out.println("=============================================================");
//
//			if (rs.next()) {
//				for(int i=0;i<5;i++){
//					String str = rs.getString(i+1);
//					if(str.equals(seat)) {
//						System.out.println(seat+"좌석 예매완료");
//						break;
//					}
//				}
//			
//			}else {
//				System.out.println("XX");
//			}
//
//			
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			close.execClose(rs, pstmt, con);
//		}
//	}
}
