package chess.domain.position;

import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;

public enum Direction {
	E((start, end) ->  start.getColumnGap(end) < 0 && start.getRowGap(end) == 0, Position::right),
	W((start, end) ->  start.getColumnGap(end) > 0 && start.getRowGap(end) == 0, Position::left),
	S((start, end) ->  start.getColumnGap(end) == 0 && start.getRowGap(end) > 0, Position::down),
	N((start, end) ->  start.getColumnGap(end) == 0 && start.getRowGap(end) < 0, Position::up),
	NE((start, end) ->  start.getColumnGap(end) < 0 && start.getRowGap(end) < 0, position -> position.up().right()),
	SW((start, end) ->  start.getColumnGap(end) > 0 && start.getRowGap(end) > 0, position -> position.down().left()),
	NW((start, end) ->  start.getColumnGap(end) > 0 && start.getRowGap(end) < 0, position -> position.up().left()),
	ES((start, end) ->  start.getColumnGap(end) < 0 && start.getRowGap(end) > 0, position -> position.right().down());

	private final BiPredicate<Position, Position> directJudge;
	private final Function<Position, Position> moving;

	Direction(BiPredicate<Position, Position> directJudge, Function<Position, Position> moving) {
		this.directJudge = directJudge;
		this.moving = moving;
	}

	public static Direction of(Position start, Position end) {
		return Arrays.stream(Direction.values())
			.filter(value -> value.directJudge.test(start, end))
			.findFirst()
			.orElseThrow(NullPointerException::new);
	}

	public Position move(Position start) {
		return moving.apply(start);
	}
}
