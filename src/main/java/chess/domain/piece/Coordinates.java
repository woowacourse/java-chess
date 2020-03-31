package chess.domain.piece;

import chess.domain.position.Position;

public class Coordinates {
	private static final int MAXIMUM_DIDSTANCE_ABS = 7;

	private final int fileDistance;
	private final int rankDistance;

	public Coordinates(int distanceX, int distanceY) {
		this.fileDistance = distanceX;
		this.rankDistance = distanceY;
	}

	public static Coordinates of(Position from, Position to) {
		int fileDistance = to.getFileNumber() - from.getFileNumber();
		int rankDistance = to.getRankNumber() - from.getRankNumber();
		validateDistanceSize(fileDistance, rankDistance);
		return new Coordinates(fileDistance, rankDistance);
	}

	private static void validateDistanceSize(int fileDistance, int rankDistance) {
		if (Math.abs(fileDistance) > MAXIMUM_DIDSTANCE_ABS || Math.abs(rankDistance) > MAXIMUM_DIDSTANCE_ABS) {
			throw new IllegalArgumentException("좌표 거리가 범위 밖입니다.");
		}
	}
}
