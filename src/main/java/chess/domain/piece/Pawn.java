package chess.domain.piece;

import static chess.domain.piece.Color.*;
import static chess.domain.position.Direction.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import chess.domain.board.Board;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.position.Row;

public class Pawn extends FixedPiece {
	public static final String WHITE_PAWN_ROW = "2";
	public static final String BLACK_PAWN_ROW = "7";
	private static final double PAWN_SCORE = 1;
	private static final List<Row> INITIAL_ROW = Arrays.asList(Row.of(WHITE_PAWN_ROW), Row.of(BLACK_PAWN_ROW));

	private List<Direction> eatableDirections;

	public Pawn(String name, Color color) {
		super(name, color, createMovableDirection(color), PAWN_SCORE);
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

	public Set<Position> findStraightNext(Position position, Direction direction, Board board) {
		Set<Position> movablePositions = new HashSet<>();
		Position startPosition = position;
		if (position.canNotMoveNext(direction)) {
			return movablePositions;
		}
		position = position.next(direction);
		if (board.isNotEmptyPosition(position)) {
			return movablePositions;
		}
		movablePositions.add(position);

		if (isPawnAtDefaultPosition(startPosition)) {
			movablePositions.addAll(findStraightNext(position, direction, board));
		}

		return movablePositions;
	}

	private boolean isPawnAtDefaultPosition(Position startPosition) {
		return INITIAL_ROW.contains(startPosition.getRow());
	}

	private Set<Position> findEatablePosition(Position currentPosition, Board board) {
		Set<Position> eatablePositions = new HashSet<>();
		for (Direction direction : eatableDirections) {
			eatablePositions.addAll(findEatableNext(currentPosition, direction, board));
		}
		return eatablePositions;
	}

	protected Set<Position> findEatableNext(Position currentPosition, Direction direction, Board board) {
		Set<Position> eatablePosition = new HashSet<>();
		if (currentPosition.canNotMoveNext(direction)) {
			return eatablePosition;
		}
		currentPosition = currentPosition.next(direction);
		if (board.isNotEmptyPosition(currentPosition) && isEnemy(board.findPieceBy(currentPosition))) {
			eatablePosition.add(currentPosition);
		}
		return eatablePosition;
	}
}
