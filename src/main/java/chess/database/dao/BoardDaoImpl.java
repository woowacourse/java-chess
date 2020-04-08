package chess.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.database.DataSource;
import chess.domain.board.Board;
import chess.domain.board.Cell;
import chess.domain.coordinates.Coordinates;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.util.PieceNameConverter;

public class BoardDaoImpl implements BoardDao {
	private final DataSource dataSource;

	public BoardDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insertBoard(Board board) {
		String query = "INSERT INTO board VALUES(?, ?)";
		List<Cell> cells = board.getPiecesAsList();
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			prepareInsertStatements(cells, preparedStatement);
			preparedStatement.executeBatch();
		} catch (SQLException exception) {
			System.err.println(exception.getMessage());
		}
	}

	private void prepareInsertStatements(List<Cell> cells, PreparedStatement preparedStatement) throws SQLException {
		for (Cell cell : cells) {
			preparedStatement.setString(1, cell.getCoordinatesName());
			preparedStatement.setString(2, cell.getPieceName());
			preparedStatement.addBatch();
			preparedStatement.clearParameters();
		}
	}

	@Override
	public Piece findPieceBy(Coordinates coordinates) {
		String query = "SELECT * FROM board WHERE position = (?)";
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, coordinates.getName());
			ResultSet resultSet = preparedStatement.executeQuery();
			return PieceFactory.createByName(resultSet.getString(2));
		} catch (SQLException exception) {
			System.err.println(exception.getMessage());
		}
		return null;
	}

	@Override
	public Board getBoard() {
		String query = "SELECT * FROM board";
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			Map<Coordinates, Piece> pieces = mapBoard(resultSet);
			return new Board(pieces);
		} catch (SQLException exception) {
			System.err.println(exception.getMessage());
		}
		return null;
	}

	private Map<Coordinates, Piece> mapBoard(ResultSet resultSet) throws SQLException {
		Map<Coordinates, Piece> pieces = new HashMap<>();
		while (resultSet.next()) {
			Coordinates coordinates = Coordinates.of(resultSet.getString(1));
			Piece piece = PieceFactory.createByName(resultSet.getString(2));
			pieces.put(coordinates, piece);
		}
		return pieces;
	}

	@Override
	public void insertOrUpdatePieceBy(Coordinates coordinates, Piece piece) {
		String query = "INSERT INTO board VALUES(?, ?) ON DUPLICATE KEY UPDATE piece_type=(?)";
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, coordinates.getName());
			preparedStatement.setString(2, PieceNameConverter.toPieceType(piece));
			preparedStatement.setString(3, PieceNameConverter.toPieceType(piece));
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			System.err.println(exception.getMessage());
		}
	}

	@Override
	public void deleteBoard() {
		String query = "DELETE FROM board";
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			System.err.println(exception.getMessage());
		}
	}

	@Override
	public void deletePieceBy(Coordinates coordinates) {
		String query = "DELETE FROM board WHERE position = (?)";
		try (Connection connection = dataSource.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, coordinates.getName());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			System.err.println(exception.getMessage());
		}
	}
}
