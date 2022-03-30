package chess.domain.direction.strategy;

import java.util.stream.Stream;

import chess.domain.direction.BasicDirection;
import chess.domain.direction.DiagonalDirection;
import chess.domain.direction.Direction;
import chess.domain.position.Position;

public class WhitePawnDirectionStrategy implements DirectionStrategy {

	private static final String INVALID_DIRECTION_PAWN = "White Pawn이 갈 수 없는 방향입니다.";

	@Override
	public Direction find(Position from, Position to) {
		return Stream.of(BasicDirection.NORTH, DiagonalDirection.NORTH_EAST, DiagonalDirection.NORTH_WEST)
			.filter(direction -> direction.confirm(from, to))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(INVALID_DIRECTION_PAWN));
	}
}
