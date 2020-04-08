package chess.domain.game.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.domain.game.Game;
import chess.domain.state.GameStateFactory;

public class MySQLGameDAO implements GameDAO{
	public Connection getConnection() {
		Connection con = null;
		String server = "localhost:13306";
		String database = "chess";
		String option = "?useSSL=false&serverTimezone=UTC";
		String userName = "root";
		String password = "root";

		try {
			con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
		} catch (SQLException e) {
			System.err.println("연결 오류:" + e.getMessage());
			e.printStackTrace();
		}

		return con;
	}

	public void closeConnection(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.err.println("con 오류:" + e.getMessage());
		}
	}

	public Game findById(int userId) throws SQLException {
		String query = "SELECT * FROM game WHERE id = ?";
		Connection connection = getConnection();
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, userId);
		ResultSet rs = pstmt.executeQuery();

		if (!rs.next()) return null;


		return new Game(GameStateFactory.of(
			rs.getString("state"),
			rs.getString("turn"),
			rs.getString("board")
			));
	}

	public void update(Game game) throws SQLException {
		String query = "UPDATE game SET state=?, turn=?, board=? WHERE id=?";
		Connection connection = getConnection();
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, game.getStateType().getState());
		pstmt.setString(2, game.getTurn().name());
		pstmt.setString(3, game.getBoard().getAsString());
		pstmt.setInt(4, game.getId());
		pstmt.executeUpdate();
	}
}
