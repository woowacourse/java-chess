package chess.dao;

import static chess.domain.piece.PieceType.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.domain.Board;
import chess.domain.color.Color;
import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class ChessBoardDao implements ChessDao {
	private static final DBConnection dbConnection = new DBConnection();

	@Override
	public void save(Board chessBoard) {
		Map<Position, Piece> board = chessBoard.board();
		for (Map.Entry<Position, Piece> boardEntry : board.entrySet()) {
			insert(chessBoard, boardEntry);
		}
	}

	private void insert(final Board chessBoard, final Map.Entry<Position, Piece> boardEntry) {
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

	@Override
	public Board select() {
		Map<Position, Piece> board = new HashMap<>();
		Color turn = null;
		final var query = "SELECT piece_type, piece_rank, piece_file, color, turn from chess_game";
		try (final var connection = dbConnection.getConnection();
			 final var preparedStatement = connection.prepareStatement(query)) {
			final var resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				PieceType pieceType = valueOf(resultSet.getString("piece_type"));
				Rank rank = Rank.from(resultSet.getInt("piece_rank"));
				File file = File.from(resultSet.getString("piece_file").charAt(0));
				Color color = Color.valueOf(resultSet.getString("color"));
				turn = Color.valueOf(resultSet.getString("turn"));
				Position position = Position.of(file, rank);
				Piece piece = toPiece(pieceType, color, position);
				board.put(position, piece);
			}
		} catch (final SQLException e) {
			throw new RuntimeException(e);
		}
		if (board.isEmpty()) {
			return null;
		}
		return new Board(board, turn);
	}

	private Piece toPiece(final PieceType pieceType, final Color color, final Position position) {
		if (pieceType == ROOK) {
			return new Rook(color, position);
		}
		if (pieceType == BISHOP) {
			return new Bishop(color, position);
		}
		if (pieceType == KNIGHT) {
			return new Knight(color, position);
		}
		if (pieceType == QUEEN) {
			return new Queen(color, position);
		}
		if (pieceType == KING) {
			return new King(color, position);
		}
		if (pieceType == PAWN) {
			return new Pawn(color, position);
		}
		return new Empty(Color.NONE, position);
	}

	@Override
	public void update(final Board board) {
		delete();
		save(board);
	}

	public void delete() {
		final var query = "DELETE FROM chess_game";
		try (final var connection = dbConnection.getConnection();
			 final var preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.executeUpdate();
		} catch (final SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void init() {
		final var query = "TRUNCATE TABLE chess_game";
		try (final var connection = dbConnection.getConnection();
			 final var preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.executeUpdate();
		} catch (final SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
