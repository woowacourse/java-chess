package chess.domain.piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import chess.domain.board.Board;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class StretchMovingStrategy implements MovingStrategy {
	protected List<Direction> movableDirections;

	public StretchMovingStrategy(List<Direction> movableDirections) {
		this.movableDirections = movableDirections;
	}

	@Override
	public Set<Position> findMovablePositions(Position currentPosition, Board board) {
		Set<Position> movablePositions = new HashSet<>();
		for (Direction direction : movableDirections) {
			movablePositions.addAll(findNext(currentPosition, direction, board));
		}
		return movablePositions;
	}

	public Set<Position> findNext(Position startPosition, Direction direction, Board board) {
		Set<Position> movablePositions = new HashSet<>();
		Piece target = board.findPieceBy(startPosition);

		Position currentPosition = startPosition;
		while (currentPosition.canMoveNext(direction)) {
			currentPosition = currentPosition.next(direction);
			if (board.isNotEmptyPosition(currentPosition)) {
				break;
			}
			movablePositions.add(currentPosition);
		}
		if (board.isNotEmptyPosition(currentPosition) && target.isEnemy(board.findPieceBy(currentPosition))) {
			movablePositions.add(currentPosition);
		}
		return movablePositions;
	}
}
