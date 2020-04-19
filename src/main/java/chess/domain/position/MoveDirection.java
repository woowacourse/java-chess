package chess.domain.position;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.UnaryOperator;

public enum MoveDirection {

	N((fileGap, rankGap) -> fileGap == 0 && rankGap > 0,
		(sourcePosition) -> sourcePosition.move(0, 1)),
	NE((fileGap, rankGap) -> isSameGap(fileGap, rankGap) && fileGap >= 0 && rankGap >= 0,
		(sourcePosition) -> sourcePosition.move(1, 1)),
	E((fileGap, rankGap) -> fileGap > 0 && rankGap == 0,
		(sourcePosition) -> sourcePosition.move(1, 0)),
	SE((fileGap, rankGap) -> isSameGap(fileGap, rankGap) && fileGap >= 0 && rankGap <= 0,
		(sourcePosition) -> sourcePosition.move(1, -1)),
	S((fileGap, rankGap) -> fileGap == 0 && rankGap < 0,
		(sourcePosition) -> sourcePosition.move(0, -1)),
	SW((fileGap, rankGap) -> isSameGap(fileGap, rankGap) && fileGap <= 0 && rankGap <= 0,
		(sourcePosition) -> sourcePosition.move(-1, -1)),
	W((fileGap, rankGap) -> fileGap < 0 && rankGap == 0,
		(sourcePosition) -> sourcePosition.move(-1, 0)),
	NW((fileGap, rankGap) -> isSameGap(fileGap, rankGap) && fileGap <= 0 && rankGap >= 0,
		(sourcePosition) -> sourcePosition.move(-1, 1));

	private final BiPredicate<Integer, Integer> isSameDirection;
	private final UnaryOperator<Position> moveNextPosition;

	MoveDirection(final BiPredicate<Integer, Integer> isSameDirection, final UnaryOperator<Position> moveNextPosition) {
		this.isSameDirection = isSameDirection;
		this.moveNextPosition = moveNextPosition;
	}

	private static boolean isSameGap(final int fileGap, final int rankGap) {
		return (Math.abs(fileGap) - Math.abs(rankGap)) == 0;
	}

	public boolean isSameDirectionFrom(final Position sourcePosition, final Position targetPosition) {
		final int chessFileGap = sourcePosition.calculateChessFileGapTo(targetPosition);
		final int chessRankGap = sourcePosition.calculateChessRankGapTo(targetPosition);

		return this.isSameDirection.test(chessFileGap, chessRankGap);
	}

	public Position move(final Position sourcePosition) {
		Objects.requireNonNull(sourcePosition, "유효한 위치가 아닙니다.");
		return moveNextPosition.apply(sourcePosition);
	}

}
