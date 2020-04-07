package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import chess.domain.Board;
import chess.domain.Pieces;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceRule;

public class BoardDao {

	public Connection getConnection() {
		Connection con = null;
		String server = "localhost:13306"; // MySQL 서버 주소
		String database = "chess"; // MySQL DATABASE 이름
		String option = "?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
		String userName = "root"; //  MySQL 서버 아이디
		String password = "root"; // MySQL 서버 비밀번호

		// 드라이버 로딩
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
			e.printStackTrace();
		}

		// 드라이버 연결
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
		} catch (SQLException e) {
			System.err.println("연결 오류:" + e.getMessage());
			e.printStackTrace();
		}

		return con;
	}

	public Board find() throws SQLException {
		final String sql = "SELECT type, position, team FROM piece";

		try (
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery()
		) {
			Map<Position, Piece> positionPairs = new HashMap<>();
			while (rs.next()) {
				String type = rs.getString("type");
				String position = rs.getString("position");
				String team = rs.getString("team");
				positionPairs.put(new Position(position), PieceRule.makeNewPiece(type.charAt(0), position, team));
			}
			return new Board(new Pieces(positionPairs));
		}
	}

	public void save(Board board) throws SQLException {
		try (
			Connection connection = getConnection()
		) {
			removeAll(connection);
			for (Piece alivePiece : board.getPieces().getAlivePieces()) {
				savePiece(connection, alivePiece);
			}
		}
	}

	private void removeAll(Connection connection) throws SQLException {
		String sql = "DELETE FROM piece";
		Statement statement = connection.createStatement();
		statement.execute(sql);
	}

	private void savePiece(Connection connection, Piece piece) throws SQLException {
		String sql = "INSERT INTO piece(position, type, team) VALUES (?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, piece.getPosition());
		statement.setString(2, piece.toString());
		statement.setString(3, piece.getTeam().getName());
		statement.executeUpdate();
	}
}