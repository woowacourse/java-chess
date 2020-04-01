package chess.domain.position;

import java.util.Arrays;
import java.util.List;
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

	private BiPredicate<Integer, Integer> isSameDirection;
	private UnaryOperator<Position> moveNextPosition;

	MoveDirection(BiPredicate<Integer, Integer> isSameDirection, UnaryOperator<Position> moveNextPosition) {
		this.isSameDirection = isSameDirection;
		this.moveNextPosition = moveNextPosition;
	}

	private static boolean isSameGap(int fileGap, int rankGap) {
		return (Math.abs(fileGap) - Math.abs(rankGap)) == 0;
	}

	public boolean isSameDirectionFrom(Position sourcePosition, Position targetPosition) {
		int chessFileGap = sourcePosition.calculateChessFileGapTo(targetPosition);
		int chessRankGap = sourcePosition.calculateChessRankGapTo(targetPosition);

		return this.isSameDirection.test(chessFileGap, chessRankGap);
	}

	public Position move(Position sourcePosition) {
		Objects.requireNonNull(sourcePosition, "유효한 위치가 아닙니다.");
		return moveNextPosition.apply(sourcePosition);
	}

	public static List<MoveDirection> getQueenMovableDirections() {
		return Arrays.asList(values());
	}

	public static List<MoveDirection> getRookMovableDirections() {
		return Arrays.asList(N, E, S, W);
	}

	public static List<MoveDirection> getBishopMovableDirections() {
		return Arrays.asList(NE, SE, SW, NW);
	}

	public static List<MoveDirection> getBlackPawnMovableDirections() {
		return Arrays.asList(S);
	}

	public static List<MoveDirection> getBlackPawnCatchableDirections() {
		return Arrays.asList(SW, SE);
	}

	public static List<MoveDirection> getWhitePawnMovableDirections() {
		return Arrays.asList(N);
	}

	public static List<MoveDirection> getWhitePawnCatchableDirections() {
		return Arrays.asList(NW, NE);
	}

}
