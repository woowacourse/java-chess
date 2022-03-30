package chess.domain.position;

import java.util.Objects;

public class Square {
	private static final int FILE_INDEX = 0;
	private static final int RANK_INDEX = 1;
	private final File file;
	private final Rank rank;

	public Square(String position) {
		this(File.find(position.charAt(FILE_INDEX)), Rank.find(position.charAt(RANK_INDEX)));
	}

	public Square(File file, Rank rank) {
		this.file = file;
		this.rank = rank;
	}

	public Direction getGap(Square target) {
		int gapOfFile = file.getGap(target.file);
		int gapOfRank = rank.getGap(target.rank);
		return new Direction(gapOfFile, gapOfRank);
	}

	public Square add(Direction direction) {
		return direction.add(file, rank);
	}

	public boolean checkFile(File file) {
		return this.file == file;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Square square = (Square)o;
		return file == square.file && rank == square.rank;
	}

	@Override
	public int hashCode() {
		return Objects.hash(file, rank);
	}

}
