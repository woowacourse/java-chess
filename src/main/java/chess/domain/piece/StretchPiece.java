package chess.domain.piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import chess.domain.Board;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public abstract class StretchPiece extends AbstractPiece {
	protected List<Direction> movableDirections;

	public StretchPiece(String name, Color color, List<Direction> movableDirections) {
		super(name, color);
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

		Position currentPosition = startPosition;
		while (currentPosition.canMoveNext(direction)) {
			currentPosition = currentPosition.next(direction);
			if (board.isNotEmptyPosition(currentPosition)) {
				break;
			}
			movablePositions.add(currentPosition);
		}
		if (board.isNotEmptyPosition(currentPosition) && isEnemy(board.findPieceBy(currentPosition))) {
			movablePositions.add(currentPosition);
		}
		return movablePositions;
	}

	@Deprecated
	public List<Position> findNext1(Position startPosition, Position currentPosition, Direction direction,
		Board board) {
		if (!currentPosition.canMoveNext(direction) || board.isNotEmptyPosition(currentPosition)) {
			return Collections.emptyList();
		}
		if (isEnemy(board.findPieceBy(currentPosition))) {
			return Collections.singletonList(currentPosition);
		}
		List<Position> availablePositions = new ArrayList<>();
		availablePositions.add(currentPosition);
		availablePositions.addAll(Stream.of(findNext1(startPosition, currentPosition.next(direction), direction, board))
			.flatMap(Collection::stream)
			.collect(Collectors.toList()));
		return availablePositions;
	}
}
