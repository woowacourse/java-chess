package chess.piece;

import java.util.List;
import java.util.stream.Collectors;

import chess.position.File;
import chess.position.Position;
import chess.position.Rank;

public class Queen extends Piece {
	private static final String INITIAL_CHARACTER = "Q";

	public Queen(Team team) {
		super(team);
	}

	@Override
	public List<Position> findTraceBetween(Position start, Position end) {
		if (start.isNotStraight(end) && start.isNotDiagonal(end)) {
			throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
		}
		if (start.isSameRank(end)) {
			List<File> files = File.valuesBetween(start.getFile(), end.getFile());
			return files.stream()
				.map(file -> Position.of(file, start.getRank()))
				.collect(Collectors.toList());
		}
		if (start.isDiagonal(end)) {
			return Position.findDiagonalTrace(start, end);
		}
		List<Rank> ranks = Rank.valuesBetween(start.getRank(), end.getRank());
		return ranks.stream()
			.map(rank -> Position.of(start.getFile(), rank))
			.collect(Collectors.toList());
	}

	@Override
	protected String getInitialCharacter() {
		return INITIAL_CHARACTER;
	}
}
