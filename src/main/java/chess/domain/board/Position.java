package chess.domain.board;

import chess.domain.piece.Team;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Position {

	private static final String OVER_RANGE_ERROR = "체스판 범위를 벗어나는 입력입니다.";

	private static final Map<Rank, Map<File, Position>> cachedPositions = new HashMap<>();

	private final Rank rank;
	private final File file;

	private Position(final Rank rank, final File file) {
		this.rank = rank;
		this.file = file;
	}

	public static Position of(final Rank rank, final File file) {
		cachedPositions.putIfAbsent(rank, new HashMap<>());
		cachedPositions.get(rank).putIfAbsent(file, new Position(rank, file));
		return cachedPositions.get(rank).get(file);
	}

	public Position addDirection(Direction direction) {
		Optional<Rank> rank = direction.addRank(this.rank);
		Optional<File> file = direction.addFile(this.file);
		if (rank.isEmpty() || file.isEmpty()) {
			throw new IllegalArgumentException(OVER_RANGE_ERROR);
		}
		return Position.of(rank.get(), file.get());
	}

	public boolean isLinerMove(Position position) {
		return this.rank.equals(position.rank) || this.file.equals(position.file);
	}

	public boolean isDiagonalMove(Position position) {
		return this.rank.abs(position.rank) == this.file.abs(position.file);
	}

	public List<Position> getArrivalPositionsByDirections(List<Direction> directions) {
		return directions.stream()
				.filter(this::canMoveToDirection)
				.map(this::addDirection)
				.collect(Collectors.toList());
	}

	private boolean canMoveToDirection(Direction direction) {
		Optional<Rank> rank = direction.addRank(this.rank);
		Optional<File> file = direction.addFile(this.file);
		return rank.isPresent() && file.isPresent();
	}

	public int calculateRankDifference(Position position) {
		return this.rank.calculateDifference(position.rank);
	}

	public int calculateFileDifference(Position position) {
		return this.file.calculateDifference(position.file);
	}

	public int subtractRank(Position position) {
		return this.rank.subtract(position.rank);
	}

	public int subtractFile(Position position) {
		return this.file.subtract(position.file);
	}

	public boolean isEndFile() {
		return file.equals(File.H);
	}

	public boolean isInitialPawnRank(final Team team) {
		if (team.isBlack()) {
			return this.rank.equals(Rank.SEVEN);
		}
		return this.rank.equals(Rank.TWO);
	}

	public static List<Position> getPositions() {
		return Arrays.stream(Rank.values())
				.flatMap(Position::createRankPositions)
				.collect(Collectors.toList());
	}

	private static Stream<Position> createRankPositions(final Rank rank) {
		return Arrays.stream(File.values())
				.map(file -> Position.of(rank, file))
				.collect(Collectors.toList()).stream();
	}

	public static List<Position> getReversePositions() {
		return Arrays.stream(Rank.values())
				.sorted(Collections.reverseOrder())
				.flatMap(Position::createRankPositions)
				.collect(Collectors.toList());
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Position position = (Position) o;
		return rank == position.rank && file == position.file;
	}

	@Override
	public int hashCode() {
		return Objects.hash(rank, file);
	}

	@Override
	public String toString() {
		return "Position{" +
				"rank=" + rank +
				", file=" + file +
				'}';
	}
}
