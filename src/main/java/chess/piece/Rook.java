package chess.piece;

import java.util.List;
import java.util.stream.Collectors;

import chess.position.File;
import chess.position.Position;
import chess.position.Rank;

public class Rook extends Piece {
	private static final String SYMBOL = "R";

	public Rook(Team team) {
		super(team);
	}

	@Override
	public List<Position> findReachablePositions(Position start, Position end) {
		if (start.isNotStraight(end)) {
			throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
		}
		if (start.isSameRank(end)) {
			List<File> files = File.valuesBetween(start.getFile(), end.getFile());
			return files.stream()
				.map(file -> Position.of(file, start.getRank()))
				.collect(Collectors.toList());
		}
		List<Rank> ranks = Rank.valuesBetween(start.getRank(), end.getRank());
		return ranks.stream()
			.map(rank -> Position.of(start.getFile(), rank))
			.collect(Collectors.toList());
	}

	@Override
	protected String getSymbol() {
		return SYMBOL;
	}
}
