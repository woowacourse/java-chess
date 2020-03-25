package chess.domain.board;

import java.util.Map;

import chess.domain.piece.Piece;

/**
 *    체스 판을 의미하는 클래스입니다.
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class ChessBoard {
	// private final Map<Square, Piece> board
	public static Map<Square, Piece> board;

	public static Piece getPiece(Square square) {
		return board.get(square);
	}

	public void init() {

	}
}
