package chess.domain.piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import chess.domain.board.Board;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class FixedMovingStrategy implements MovingStrategy {
	private List<Direction> movableDirections;

	public FixedMovingStrategy(List<Direction> movableDirections) {
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

	protected Set<Position> findNext(Position currentPosition, Direction direction, Board board) {
		Set<Position> movablePositions = new HashSet<>();
		Piece target = board.findPieceBy(currentPosition);
		if (!currentPosition.canMoveNext(direction)) {
			return movablePositions;
		}
		currentPosition = currentPosition.next(direction);
		if (!board.isNotEmptyPosition(currentPosition)) {
			movablePositions.add(currentPosition);
		}
		if (board.isNotEmptyPosition(currentPosition) && target.isEnemy(board.findPieceBy(currentPosition))) {
			movablePositions.add(currentPosition);
		}
		return movablePositions;
	}
}
