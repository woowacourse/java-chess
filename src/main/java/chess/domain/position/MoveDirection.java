package chess.domain.position;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.UnaryOperator;

public enum MoveDirection {
	N((fileGap, rankGap) -> fileGap == 0 && rankGap > 0,
		(sourcePosition) -> sourcePosition.move(0, 1)),
	NE((fileGap, rankGap) -> isSameGapFrom(fileGap, rankGap) && fileGap >= 0 && rankGap >= 0,
		(sourcePosition) -> sourcePosition.move(1, 1)),
	E((fileGap, rankGap) -> fileGap > 0 && rankGap == 0,
		(sourcePosition) -> sourcePosition.move(1, 0)),
	SE((fileGap, rankGap) -> isSameGapFrom(fileGap, rankGap) && fileGap >= 0 && rankGap <= 0,
		(sourcePosition) -> sourcePosition.move(1, -1)),
	S((fileGap, rankGap) -> fileGap == 0 && rankGap < 0,
		(sourcePosition) -> sourcePosition.move(0, -1)),
	SW((fileGap, rankGap) -> isSameGapFrom(fileGap, rankGap) && fileGap <= 0 && rankGap <= 0,
		(sourcePosition) -> sourcePosition.move(-1, -1)),
	W((fileGap, rankGap) -> fileGap < 0 && rankGap == 0,
		(sourcePosition) -> sourcePosition.move(-1, 0)),
	NW((fileGap, rankGap) -> isSameGapFrom(fileGap, rankGap) && fileGap <= 0 && rankGap >= 0,
		(sourcePosition) -> sourcePosition.move(-1, 1));

	private BiPredicate<Integer, Integer> isSameDirection;
	private UnaryOperator<Position> moveByUnit;

	MoveDirection(BiPredicate<Integer, Integer> isSameDirection, UnaryOperator<Position> moveByUnit) {
		this.isSameDirection = isSameDirection;
		this.moveByUnit = moveByUnit;
	}

	public static boolean isSameGapFrom(int fileGap, int rankGap) {
		return (Math.abs(fileGap) - Math.abs(rankGap)) == 0;
	}

	public boolean isSameDirectionFrom(Position sourcePosition, Position targetPosition) {
		int fileInterval = sourcePosition.calculateChessFileGapTo(targetPosition);
		int rankInterval = sourcePosition.calculateChessRankGapTo(targetPosition);

		return this.isSameDirection.test(fileInterval, rankInterval);
	}

	public Position move(Position sourcePosition) {
		Objects.requireNonNull(sourcePosition, "유효한 위치가 아닙니다.");
		return moveByUnit.apply(sourcePosition);
	}
}
