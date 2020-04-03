package chess.domain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.domain.dto.PieceDto;
import chess.domain.dto.PieceEditDto;
import chess.domain.position.Position;

public class BoardDao {
	private Connection connection;

	public BoardDao(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}

	public void addPiece(PieceDto pieceDto) throws SQLException {
		String query = "INSERT INTO piece VALUES (?, ?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, pieceDto.getPositionValue());
		statement.setString(2, pieceDto.getPieceName());
		statement.executeUpdate();
	}

	public PieceDto findPiece(Position position) throws SQLException {
		String query = "SELECT * FROM piece WHERE position = ?";
		PreparedStatement statement = connection.prepareStatement(query);
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
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet result = statement.executeQuery();

		while (result.next()) {
			results.add(PieceDto.of(result.getString("position"),
				result.getString("name")));
		}

		return results;
	}

	public void editPieceByPosition(PieceEditDto pieceEditDto) throws SQLException {
		String query = "INSERT INTO piece VALUE (?, ?) ON DUPLICATE KEY UPDATE name = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, pieceEditDto.getTargetPositionValue());
		statement.setString(2, pieceEditDto.getWantPieceName());
		statement.setString(3, pieceEditDto.getWantPieceName());
		statement.executeUpdate();
	}

	public void deletePieceByPosition(Position position) throws SQLException {
		String query = "DELETE FROM piece WHERE position = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, position.getValue());
		statement.executeUpdate();
	}

	public void deleteAll() throws SQLException {
		String query = "DELETE FROM piece";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.executeUpdate();
	}
}
