package chess.domain.position;

import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;

public enum Direction {
	E((start, end) -> isRight(start, end) && isHorizontal(start, end), Position::right),
	W((start, end) -> isLeft(start, end) && isHorizontal(start, end), Position::left),
	S((start, end) -> isVertical(start, end) && isDown(start, end), Position::down),
	N((start, end) -> isVertical(start, end) && isUp(start, end), Position::up),
	NE((start, end) -> isRight(start, end) && isUp(start, end), position -> position.up().right()),
	SW((start, end) -> isLeft(start, end) && isDown(start, end), position -> position.down().left()),
	NW((start, end) -> isLeft(start, end) && isUp(start, end), position -> position.up().left()),
	ES((start, end) -> isRight(start, end) && isDown(start, end), position -> position.right().down());

	private static boolean isUp(Position start, Position end) {
		return start.getRowGap(end) < 0;
	}

	private static boolean isDown(Position start, Position end) {
		return start.getRowGap(end) > 0;
	}

	private static boolean isHorizontal(Position start, Position end) {
		return start.getRowGap(end) == 0;
	}

	private static boolean isVertical(Position start, Position end) {
		return start.getColumnGap(end) == 0;
	}

	private static boolean isLeft(Position start, Position end) {
		return start.getColumnGap(end) > 0;
	}

	private static boolean isRight(Position start, Position end) {
		return start.getColumnGap(end) < 0;
	}

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
