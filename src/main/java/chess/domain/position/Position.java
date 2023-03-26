package chess.domain.position;

import java.util.Objects;

import chess.domain.move.Direction;

public final class Position {

	private final File file;
	private final Rank rank;

	private Position(final File file, final Rank rank) {
		this.file = file;
		this.rank = rank;
	}

	public static Position of(final File file, final Rank rank) {
		return new Position(file, rank);
	}

	public static Position from(final String value) {
		final File file = File.from(value.charAt(0));
		final Rank rank = Rank.from(value.charAt(1) - '0');
		return new Position(file, rank);
	}

	public boolean isSame(final Position target) {
		return file == target.file && rank == target.rank;
	}

	public Position nextPosition(final Direction unit) {
		File newFile = file.nextFile(unit.dx());
		Rank newRank = rank.nextRank(unit.dy());
		return Position.of(newFile, newRank);
	}

	public File file() {
		return file;
	}

	public char fileValue() {
		return file.value();
	}

	public Rank rank() {
		return rank;
	}

	public int rankValue() {
		return rank.value();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Position position = (Position)o;
		return file == position.file && rank == position.rank;
	}

	@Override
	public int hashCode() {
		return Objects.hash(file, rank);
	}
}
