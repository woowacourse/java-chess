package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.board.PieceFactory;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.PieceDto;

public class BoardDao {
	public void addPiece(PieceDto pieceDto) {
		try (Connection connection = new SQLConnector().getConnection()) {
			String query = "INSERT INTO piece VALUES (?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, pieceDto.getPositionValue());
			statement.setString(2, pieceDto.getPieceName());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void addAllPieces(List<PieceDto> pieces) {
		try (Connection connection = new SQLConnector().getConnection()) {
			String query = "INSERT INTO piece VALUES (?, ?)";
			for (PieceDto piece : pieces) {
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, piece.getPositionValue());
				statement.setString(2, piece.getPieceName());
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Piece findPiece(Position position) {
		try (Connection connection = new SQLConnector().getConnection()) {
			String query = "SELECT * FROM piece WHERE position = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, position.getValue());
			ResultSet result = statement.executeQuery();

			if (!result.next()) {
				return null;
			}

			String name = result.getString("name");
			return PieceFactory.of(name);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Map<Position, Piece> findAllPieces() {
		try (Connection connection = new SQLConnector().getConnection()) {

			Map<Position, Piece> pieces = new HashMap<>();
			String query = "SELECT * FROM piece";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				pieces.put(Position.of(result.getString("position")),
					PieceFactory.of(result.getString("name")));
			}
			return pieces;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void editPieceByPosition(Position position, Piece piece) {
		try (Connection connection = new SQLConnector().getConnection()) {

			String query = "INSERT INTO piece VALUE (?, ?) ON DUPLICATE KEY UPDATE name = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, position.getValue());
			statement.setString(2, piece.getName());
			statement.setString(3, piece.getName());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void deletePieceByPosition(Position position) {
		try (Connection connection = new SQLConnector().getConnection()) {
			String query = "DELETE FROM piece WHERE position = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, position.getValue());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void deleteAll() {
		try (Connection connection = new SQLConnector().getConnection()) {
			String query = "DELETE FROM piece";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
