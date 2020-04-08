package chess.database.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.database.JDBCTemplate;
import chess.domain.board.Board;
import chess.domain.board.Cell;
import chess.domain.coordinates.Coordinates;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.util.PieceNameConverter;

public class BoardDaoImpl implements BoardDao {
	private final JDBCTemplate jdbcTemplate;

	public BoardDaoImpl(JDBCTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insertBoard(Board board) {
		String query = "INSERT INTO board VALUES(?, ?)";
		List<Cell> cells = board.getPiecesAsList();
		jdbcTemplate.update(query, (preparedStatement -> {
			prepareInsertStatements(cells, preparedStatement);
			preparedStatement.executeBatch();
		}));
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
		return jdbcTemplate.query(query,
				resultSet -> PieceFactory.createByName(resultSet.getString(2)),
				preparedStatement -> preparedStatement.setString(1, coordinates.getName()));
	}

	@Override
	public Board getBoard() {
		String query = "SELECT * FROM board";
		return jdbcTemplate.query(query, resultSet -> {
			Map<Coordinates, Piece> pieces = new HashMap<>();
			while (resultSet.next()) {
				Coordinates coordinates = Coordinates.of(resultSet.getString(1));
				Piece piece = PieceFactory.createByName(resultSet.getString(2));
				pieces.put(coordinates, piece);
			}
			return new Board(pieces);
		});
	}

	@Override
	public void insertOrUpdatePieceBy(Coordinates coordinates, Piece piece) {

		String query = "INSERT INTO board VALUES(?, ?) ON DUPLICATE KEY UPDATE piece_type=(?)";
		jdbcTemplate.update(query, preparedStatement -> {
			preparedStatement.setString(1, coordinates.getName());
			preparedStatement.setString(2, PieceNameConverter.toPieceType(piece));
			preparedStatement.setString(3, PieceNameConverter.toPieceType(piece));
		});
	}

	@Override
	public void deleteBoard() {
		String query = "DELETE FROM board";
		jdbcTemplate.update(query);
	}

	@Override
	public void deletePieceBy(Coordinates coordinates) {
		String query = "DELETE FROM board WHERE position = (?)";
		jdbcTemplate.update(query, preparedStatement -> preparedStatement.setString(1, coordinates.getName()));
	}
}
