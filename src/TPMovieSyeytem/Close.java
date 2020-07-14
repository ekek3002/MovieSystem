package TPMovieSyeytem;

import java.sql.*;

public class Close {
	
	void execClose(ResultSet rs, PreparedStatement pstmt, Connection con) throws Exception {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException sqle) {
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException sqle) {
			}

		if (con != null)
			try {
				con.close();
			} catch (SQLException sqle) {
			}

	}
}







