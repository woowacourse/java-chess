package chess.piece;

import java.util.List;
import java.util.stream.Collectors;

import chess.position.File;
import chess.position.Position;
import chess.position.Rank;

public class Rook extends Piece {
	public Rook(Team team) {
		super(team, "R");
	}

	@Override
	public boolean isInvalidMovementWithoutConsideringOtherPieces(Position source, Position target) {
		return source.isNotStraight(target);
	}

	@Override
	public List<Position> movePathExceptSourceAndTarget(Position start, Position end) {
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
}
