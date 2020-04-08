package chess;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import chess.util.JDBCConnector;

public class AutoIncrementTest {
	public static void applyAutoIncrementToZero() throws SQLException {
		String query = "ALTER TABLE room AUTO_INCREMENT = 0";
		PreparedStatement pstmt = JDBCConnector.getConnection().prepareStatement(query);
		pstmt.executeUpdate();
	}
}
