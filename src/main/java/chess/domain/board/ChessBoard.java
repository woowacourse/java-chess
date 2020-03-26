package chess.domain.board;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;

/**
 *    체스 판을 의미하는 클래스입니다.
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class ChessBoard {
	public static Map<Position, Piece> board;

	public ChessBoard() {
		board = new HashMap<>();
		PieceFactory.create(board);
	}

}
