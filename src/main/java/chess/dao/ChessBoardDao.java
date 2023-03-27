package chess.dao;

import java.sql.SQLException;
import java.util.Map;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class ChessBoardDao implements ChessDao {
	private static final DBConnection dbConnection = new DBConnection();

	@Override
	public void save(Board chessBoard) {
		Map<Position, Piece> board = chessBoard.board();
		for (Map.Entry<Position, Piece> boardEntry : board.entrySet()) {
			final var query = "INSERT INTO chess_game(piece_type, piece_rank, piece_file, color, turn) VALUES (?, ?, ?, ?, ?)";
			try (final var connection = dbConnection.getConnection();
				 final var preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, boardEntry.getValue().type().name());
				preparedStatement.setInt(2, boardEntry.getValue().position().rankValue());
				preparedStatement.setString(3, boardEntry.getValue().position().file().name().toLowerCase());
				preparedStatement.setString(4, boardEntry.getValue().color().name());
				preparedStatement.setString(5, chessBoard.turn().name());
				preparedStatement.executeUpdate();
			} catch (final SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public Board select() {
		return null;
	}

	@Override
	public void update(Board board) {

	}

	@Override
	public void init(Board board) {

	}
}
