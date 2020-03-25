package chess.move;

import java.util.List;

import chess.Board;
import chess.position.Position;

public class RookMoveValidateStrategy extends MoveValidateStrategy {
	public RookMoveValidateStrategy(Board board) {
		super(board);
	}

	@Override
	public boolean isMovable(List<Position> traces) {
		return board.isNotExistAt(traces);
	}
}
