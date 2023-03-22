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

	public static Position side(final String value) {
		final File file = convertFile(value);
		final Rank rank = Rank.from(value.charAt(1) - '0');
		return new Position(file, rank);
	}

	public static Position oppsite(final String value) {
		final File file = File.from(value.charAt(0));
		final Rank rank = convertRank(value);
		return new Position(file, rank);
	}

	public static Position diagonal(final String value) {
		final File file = convertFile(value);
		final Rank rank = convertRank(value);
		return new Position(file, rank);
	}

	private static File convertFile(final String value) {
		return File.from((char)('h' - value.charAt(0) + 'a'));
	}

	private static Rank convertRank(final String value) {
		return Rank.from(9 - (value.charAt(1) - '0'));
	}

	public File file() {
		return file;
	}

	public char fileValue() {
		return file.fileValue();
	}

	public Rank rank() {
		return rank;
	}

	public int rankValue() {
		return rank.rankValue();
	}

	public boolean isSame(final Position target) {
		return file == target.file && rank == target.rank;
	}

	public Position setNextPosition(final Direction unit) {
		File newFile = file.setNextFile(unit.getDx());
		Rank newRank = rank.setNextRank(unit.getDy());
		return Position.of(newFile, newRank);
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
