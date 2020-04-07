package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.domain.game.ChessGame;
import chess.domain.game.state.State;
import chess.domain.game.state.StateFactory;
import chess.dto.BoardDto;

public class JDBCChessGameDao implements ChessGameDao {
	private static final String server = "localhost:3306"; // MySQL 서버 주소
	private static final String database = "chess_game"; // MySQL DATABASE 이름
	private static final String option = "?useSSL=false&serverTimezone=UTC";
	private static final String userName = "root"; //  MySQL 서버 아이디
	private static final String password = "root"; // MySQL 서버 비밀번호

	private static Connection connection;

	private static Connection getConnection() {
		if (connection != null) {
			return connection;
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName,
					password);
			System.out.println("정상적으로 연결되었습니다.");
		} catch (SQLException e) {
			System.err.println("연결 오류:" + e.getMessage());
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public int create() throws SQLException {
		String query = "INSERT INTO chess_game(state) VALUES (?)";
		PreparedStatement pstmt = getConnection().prepareStatement(query, new String[] {"id"});
		pstmt.setString(1, "READY");
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if (!rs.next()) {
			return -1;
		}
		return rs.getInt(1);
	}

	@Override
	public List<Integer> findAll() throws SQLException {
		String query = "SELECT id FROM chess_game";
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		List<Integer> ids = new ArrayList<>();
		while (rs.next()) {
			ids.add(rs.getInt("id"));
		}
		return ids;
	}

	@Override
	public ChessGame findById(int id) throws SQLException {
		String query = "SELECT * FROM chess_game WHERE id = ?";
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		if (!rs.next()) {
			return null;
		}
		State state = StateFactory.valueOf(rs.getString("state"))
				.create(rs.getString("board"), rs.getString("turn"));
		return new ChessGame(state);
	}

	@Override
	public void update(int id, ChessGame chessGame) throws SQLException {
		String query = "UPDATE chess_game SET state = ?, board = ?, turn = ? WHERE id=?";
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		String board = String.join("", new BoardDto(chessGame.board()).getBoard());
		String turn = chessGame.turn().getColor().toString();
		String state = chessGame.getState().toString();
		pstmt.setString(1, state);
		pstmt.setString(2, board);
		pstmt.setString(3, turn);
		pstmt.setInt(4, id);
		pstmt.executeUpdate();
	}
}
