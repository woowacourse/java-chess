package chess.domain.chessPiece.position;

import chess.domain.chessPiece.piece.Pawn;
import chess.domain.movepattern.Direction;

import java.util.Objects;

public class Position {
	private static final int FILE_INDEX = 0;
	private static final int RANK_INDEX = 1;
	private static final String FILE_RANK_DELIMITER = "";

	private File file;
	private Rank rank;

	private Position(File file, Rank rank) {
		this.file = file;
		this.rank = rank;
	}

	public static Position of(String coordinate) {
		String[] fileAndRank = coordinate.split(FILE_RANK_DELIMITER);
		File file = File.of(fileAndRank[FILE_INDEX]);
		Rank rank = Rank.of(fileAndRank[RANK_INDEX]);
		return Position.of(file, rank);
	}

	public static Position of(File file, Rank rank) {
		return new Position(file, rank);
	}

	public static Position of(Position position) {
		return Position.of(position.file, position.rank);
	}

	public void move(Direction direction) {
		int x = direction.getXDegree();
		int y = direction.getYDegree();
		this.file = File.of(this.file.getNumber() + x);
		this.rank = Rank.of(this.rank.getNumber() + y);
	}

	public boolean isPawnStartLine(Pawn pawn) {
		if (pawn.isBlackTeam()) {
			return this.rank == Rank.TWO;
		}
		return this.rank == Rank.SEVEN;
	}

	public boolean isFileA() {
		return file == File.A;
	}

	public boolean isSameRank(Position target) {
		return this.rank == target.rank;
	}

	public boolean isSameFile(Position target) {
		return isSameFile(target.file);
	}

	public boolean isSameFile(File file) {
		return this.file == file;
	}

	public int calculateRankDistance(Position target) {
		return rank.calculateDistance(target.rank);
	}

	public int calculateFileDistance(Position target) {
		return file.calculateDistance(target.file);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Position position = (Position) o;
		return file == position.file &&
				rank == position.rank;
	}

	@Override
	public int hashCode() {
		return Objects.hash(file, rank);
	}

	@Override
	public String toString() {
		return file.getName() + rank.getNumber();
	}

	public String getFile() {
		return file.getName();
	}

	public String getRank() {
		return String.valueOf(rank.getNumber());
	}
}
