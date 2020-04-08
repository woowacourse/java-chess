package chess.database.dao;

import chess.domain.board.Board;
import chess.domain.coordinates.Coordinates;
import chess.domain.piece.Piece;

public interface BoardDao {
	void insertBoard(Board board);

	Piece findPieceBy(Coordinates coordinates);

	Board getBoard();

	void insertOrUpdatePieceBy(Coordinates coordinates, Piece piece);

	void deleteBoard();

	void deletePieceBy(Coordinates coordinates);
}
