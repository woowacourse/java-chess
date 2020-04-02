package chess.domain.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.domain.piece.PieceDto;
import chess.domain.piece.PieceEditDto;
import chess.domain.position.Position;

public class BoardDao {
	public Connection getConnection() {
		Connection connection = null;
		String server = "localhost:13306"; // MySQL 서버 주소
		String database = "chess"; // MySQL DATABASE 이름
		String option = "?useSSL=false&serverTimezone=UTC";
		String userName = "root"; //  MySQL 서버 아이디
		String password = "root"; // MySQL 서버 비밀번호

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

	public void addPiece(PieceDto pieceDto) throws SQLException {
		String query = "INSERT INTO piece VALUES (?, ?)";
		PreparedStatement statement = getConnection().prepareStatement(query);
		statement.setString(1, pieceDto.getPositionValue());
		statement.setString(2, pieceDto.getPieceName());
		statement.executeUpdate();
	}

	public PieceDto findPiece(Position position) throws SQLException {
		String query = "SELECT * FROM piece WHERE position = ?";
		PreparedStatement statement = getConnection().prepareStatement(query);
		statement.setString(1, position.getValue());
		ResultSet result = statement.executeQuery();

		if (!result.next()) {
			return null;
		}

		String findPiecePosition = result.getString("position");
		String name = result.getString("name");

		return PieceDto.of(findPiecePosition, name);
	}

	public List<PieceDto> findAllPieces() throws SQLException {
		List<PieceDto> results = new ArrayList<>();
		String query = "SELECT * FROM piece";
		PreparedStatement statement = getConnection().prepareStatement(query);
		ResultSet result = statement.executeQuery();

		while (result.next()) {
			results.add(PieceDto.of(result.getString("position"),
				result.getString("name")));
		}

		return results;
	}

	public void editPieceByPosition(PieceEditDto pieceEditDto) throws SQLException {
		String query = "INSERT INTO piece VALUE (?, ?) ON DUPLICATE KEY UPDATE name = ?";
		PreparedStatement statement = getConnection().prepareStatement(query);
		statement.setString(1, pieceEditDto.getTargetPositionValue());
		statement.setString(2, pieceEditDto.getWantPieceName());
		statement.setString(3, pieceEditDto.getWantPieceName());
		statement.executeUpdate();
	}

	public void deletePieceByPosition(Position position) throws SQLException {
		String query = "DELETE FROM piece WHERE position = ?";
		PreparedStatement statement = getConnection().prepareStatement(query);
		statement.setString(1, position.getValue());
		statement.executeUpdate();
	}

	public void deleteAll() throws SQLException {
		String query = "DELETE FROM piece";
		PreparedStatement statement = getConnection().prepareStatement(query);
		statement.executeUpdate();
	}
}
