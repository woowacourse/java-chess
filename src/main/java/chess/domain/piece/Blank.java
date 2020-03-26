package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.piece.movable.KingMovable;


public class Blank extends Piece{
	public Blank() {
		super(Board.of("a1"), ".", new KingMovable(), Color.BLANK, 0);
	}
}
