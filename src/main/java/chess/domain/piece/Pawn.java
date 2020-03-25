package chess.domain.piece;

import static chess.domain.piece.Color.*;
import static chess.domain.position.Direction.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import chess.domain.Board;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class Pawn extends FixedPiece {
	private List<Direction> eatableDirections;

	public Pawn(String name, Color color) {
		super(name, color, createMovableDirection(color));
		this.eatableDirections = createEatableDirection(color);
	}

	private static List<Direction> createMovableDirection(Color color) {
		if (BLACK.equals(color)) {
			return Collections.singletonList(DOWN);
		}
		return Collections.singletonList(UP);
	}

	private List<Direction> createEatableDirection(Color color) {
		if (BLACK.equals(color)) {
			return Arrays.asList(LEFT_DOWN, RIGHT_DOWN);
		}
		return Arrays.asList(LEFT_UP, RIGHT_UP);
	}

	@Override
	public Set<Position> findMovablePositions(Position currentPosition, Board board) {
		Set<Position> movablePositions = new HashSet<>(findEatablePosition(currentPosition, board));

		for (Direction direction : movableDirections) {
			movablePositions.addAll(findStraightNext(currentPosition, direction, board));
		}
		return movablePositions;
	}

	public Set<Position> findStraightNext(Position currentPosition, Direction direction, Board board) {
		Set<Position> movablePositions = new HashSet<>();
		if (!currentPosition.canMoveNext(direction)) {
			return movablePositions;
		}
		currentPosition = currentPosition.next(direction);
		if (board.isNotEmptyPosition(currentPosition)) {
			return movablePositions;
		}
		movablePositions.add(currentPosition);
		return movablePositions;
	}

	private Set<Position> findEatablePosition(Position currentPosition, Board board) {
		Set<Position> eatablePositions = new HashSet<>();
		for (Direction direction : eatableDirections) {
			eatablePositions.addAll(findNext(currentPosition, direction, board));
		}
		return eatablePositions;
	}
}
