package chess.domain.piece.queen;

import java.util.ArrayList;
import java.util.List;

import chess.domain.piece.MoveStrategy;
import chess.domain.position.LineFactory;
import chess.domain.position.Position;

public class QueenMoveStrategy implements MoveStrategy {
	@Override
	public boolean isNotMovableTo(Position start, Position destination) {
		List<Position> movable = new ArrayList<>();
		movable.addAll(LineFactory.columnOf(start));
		movable.addAll(LineFactory.rowOf(start));
		movable.addAll(LineFactory.diagonalsOf(start));
		return !movable.contains(destination);
	}
}
