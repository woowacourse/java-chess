package chess.piece;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import chess.position.Position;
import chess.position.Rank;

public class Pawn extends Piece {
	private static final String INITIAL_CHARACTER = "P";

	private boolean hasMoved;


	public Pawn(Team team) {
		super(team);
		hasMoved = false;
	}

	@Override
	public List<Position> findMoveModeTrace(Position from, Position to) {
		if (isNotMovable(from, to) || isNotAbleToMoveDoubleSquare(from, to) || !from.isSameFile(to)) {
			throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
		}

		if ((Math.abs(to.getRankNumber() - from.getRankNumber()) == 2)) {
			List<Rank> ranks = Rank.valuesBetween(from.getRank(), to.getRank());
			return ranks.stream()
				.map(rank -> Position.of(from.getFile(), rank))
				.collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	@Override
	public List<Position> findCatchModeTrace(Position from, Position to) {
		if (from.isNotDistanceOneSquare(to) || from.isNotDiagonal(to) || isNotMovable(from, to)) {
			throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
		}
		return Collections.emptyList();
	}

	private boolean isNotMovable(Position start, Position end) {
		return !isMovable(start, end);
	}

	private boolean isMovable(Position start, Position end) {
		int rankMoveDistance = end.getRankNumber() - start.getRankNumber();
		rankMoveDistance *= team.isBlack() ? -1 : 1;
		return rankMoveDistance > 0 && rankMoveDistance <= 2;
	}

	private boolean isAbleToMoveDoubleSquare(Position start, Position end) {
		return !hasMoved || (Math.abs(end.getRankNumber() - start.getRankNumber()) != 2);
	}

	private boolean isNotAbleToMoveDoubleSquare(Position start, Position end) {
		return !isAbleToMoveDoubleSquare(start, end);
	}

	@Override
	protected String getInitialCharacter() {
		return INITIAL_CHARACTER;
	}

	@Override
	public void updateHasMoved() {
		this.hasMoved = true;
	}

	@Override
	public double getScore() {
		return 1;
	}
}
