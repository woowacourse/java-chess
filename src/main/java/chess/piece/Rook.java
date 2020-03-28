package chess.piece;

import java.util.List;
import java.util.stream.Collectors;

import chess.position.File;
import chess.position.Position;
import chess.position.Rank;

public class Rook extends Piece {
	private static final String INITIAL_CHARACTER = "R";

	public Rook(Team team) {
		super(team);
	}

	@Override
	public List<Position> findMoveModeTrace(Position from, Position to) {
		if (from.isNotStraight(to)) {
			throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
		}
		if (from.isSameRank(to)) {
			List<File> files = File.valuesBetween(from.getFile(), to.getFile());
			return files.stream()
				.map(file -> Position.of(file, from.getRank()))
				.collect(Collectors.toList());
		}
		List<Rank> ranks = Rank.valuesBetween(from.getRank(), to.getRank());
		return ranks.stream()
			.map(rank -> Position.of(from.getFile(), rank))
			.collect(Collectors.toList());
	}

	@Override
	protected String getInitialCharacter() {
		return INITIAL_CHARACTER;
	}

	@Override
	public double getScore() {
		return 5;
	}
}
