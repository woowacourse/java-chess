package chess.domain.position;

import java.util.List;
import java.util.Objects;

public final class Direction {
	private final int dFile;
	private final int dRank;

	public Direction(int dFile, int dRank) {
		this.dFile = dFile;
		this.dRank = dRank;
	}

	Square add(File file, Rank rank) {
		return new Square(file.add(dFile), rank.add(dRank));
	}

	public Direction flipAboutX() {
		return new Direction(dFile, -1 * dRank);
	}

	public boolean hasSame(List<UnitDirection> directions) {
		return directions.stream()
			.map(UnitDirection::getUnitDirection)
			.anyMatch(this::equals);
	}

	public boolean hasMultiple(List<UnitDirection> directions) {
		return directions.stream()
			.map(UnitDirection::getUnitDirection)
			.anyMatch(direction -> this.getUnitDirection().equals(direction));
	}

	public Direction getUnitDirection() {
		if (dFile == 0 || dRank == 0) {
			return getUnitDirectionWithZero();
		}

		return getUnitDirectionWithOutZero();
	}

	private Direction getUnitDirectionWithZero() {
		if (dFile == 0 && dRank == 0) {
			return new Direction(0, 0);
		}

		if (dFile == 0) {
			return new Direction(0, dRank / Math.abs(dRank));
		}

		return new Direction(dFile / Math.abs(dFile), 0);
	}

	private Direction getUnitDirectionWithOutZero() {
		int gcd = gcd(Math.abs(dFile), Math.abs(dRank));
		return new Direction(dFile / gcd, dRank / gcd);
	}

	private int gcd(int a, int b) {
		if (a % b == 0) {
			return b;
		}
		return gcd(b, a % b);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Direction direction = (Direction)o;
		return dFile == direction.dFile && dRank == direction.dRank;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dFile, dRank);
	}
}
