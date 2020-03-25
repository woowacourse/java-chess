package chess.move;

import chess.Board;
import chess.position.Position;

import java.util.List;

public class RookMoveValidateStrategy extends MoveValidateStrategy {
	public RookMoveValidateStrategy(Board board) {
		super(board);
	}

	@Override
	public boolean isMovable(List<Position> traces) {
		return board.isNotExistAt(traces);
	}
}
