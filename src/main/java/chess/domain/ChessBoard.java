package chess.domain;

import java.util.HashMap;
import java.util.Map;

import chess.domain.board.Position;
import chess.domain.piece.BlackPiecesFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.WhitePiecesFactory;

public class ChessBoard {
	Map<Position, Piece> board = new HashMap<>();

	public ChessBoard() {
		WhitePiecesFactory.create(board);
		BlackPiecesFactory.create(board);
	}

	public Piece getPiece(String position) {
		return board.get(Position.of(position));
	}
}
