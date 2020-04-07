package chess.domain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.domain.board.PieceFactory;
import chess.domain.dto.PieceDto;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class BoardDao {
	public void addPiece(PieceDto pieceDto) throws SQLException {
		Connection connection = new SQLConnector().getConnection();

		String query = "INSERT INTO piece VALUES (?, ?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, pieceDto.getPositionValue());
		statement.setString(2, pieceDto.getPieceName());
		statement.executeUpdate();
		connection.close();
	}

	public void addAllPieces(List<PieceDto> pieces) throws SQLException {
		Connection connection = new SQLConnector().getConnection();

		String query = "INSERT INTO piece VALUES (?, ?)";
		for (PieceDto piece : pieces) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, piece.getPositionValue());
			statement.setString(2, piece.getPieceName());
			statement.executeUpdate();
		}

		connection.close();
	}

	public Piece findPiece(Position position) throws SQLException {
		Connection connection = new SQLConnector().getConnection();

		String query = "SELECT * FROM piece WHERE position = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, position.getValue());
		ResultSet result = statement.executeQuery();

		if (!result.next()) {
			return null;
		}

		String name = result.getString("name");

		connection.close();
		return PieceFactory.of(name);
	}

	public List<PieceDto> findAllPieces() throws SQLException {
		Connection connection = new SQLConnector().getConnection();

		List<PieceDto> results = new ArrayList<>();
		String query = "SELECT * FROM piece";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet result = statement.executeQuery();

		while (result.next()) {
			results.add(PieceDto.of(result.getString("position"),
				result.getString("name")));
		}

		connection.close();
		return results;
	}

	public void editPieceByPosition(Position position, Piece piece) throws SQLException {
		Connection connection = new SQLConnector().getConnection();

		String query = "INSERT INTO piece VALUE (?, ?) ON DUPLICATE KEY UPDATE name = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, position.getValue());
		statement.setString(2, piece.getName());
		statement.setString(3, piece.getName());
		statement.executeUpdate();

		connection.close();
	}

	public void deletePieceByPosition(Position position) throws SQLException {
		Connection connection = new SQLConnector().getConnection();

		String query = "DELETE FROM piece WHERE position = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, position.getValue());
		statement.executeUpdate();
		connection.close();
	}

	public void deleteAll() throws SQLException {
		Connection connection = new SQLConnector().getConnection();

		String query = "DELETE FROM piece";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.executeUpdate();
		connection.close();
	}
}
