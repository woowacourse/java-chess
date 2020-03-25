package chess.domain.piece.rook;

import java.util.ArrayList;
import java.util.List;

import chess.domain.piece.MoveStrategy;
import chess.domain.position.LineFactory;
import chess.domain.position.Position;

public class RookMoveStrategy implements MoveStrategy {
	@Override
	public boolean isNotMovableTo(Position start, Position destination) {
		List<Position> movableArea = new ArrayList<>();
		movableArea.addAll(LineFactory.columnOf(start));
		movableArea.addAll(LineFactory.rowOf(start));
		return !movableArea.contains(destination);
	}
}
