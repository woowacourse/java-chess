package chess.piece;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import chess.position.Position;
import chess.position.Rank;

public class Pawn extends Piece {
	private static final String INITIAL_CHARACTER = "P";
	private static final int MAXIMUM_MOVE_DISTANCE = 2;

	private boolean hasMoved;

	public Pawn(Team team) {
		super(team);
		hasMoved = false;
	}

	@Override
	public List<Position> findTraceBetween(Position start, Position end) {
		//한칸또는두칸앞으로 가지않는경우 || 처음이 아닌데 앞으로 두칸 가려고 하는경우
		if (isNotMovable(start, end) || isNotAbleToMoveDoubleSquare(start, end)) {
			throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
		}
		if ((Math.abs(end.getRankNumber() - start.getRankNumber()) == MAXIMUM_MOVE_DISTANCE)) {
			List<Rank> ranks = Rank.valuesBetween(start.getRank(), end.getRank());
			return ranks.stream()
				.map(rank -> Position.of(start.getFile(), rank))
				.collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	private boolean isNotMovable(Position start, Position end) {
		return !isMovable(start, end);
	}

	private boolean isMovable(Position start, Position end) {
		int rankMoveDistance = end.getRankNumber() - start.getRankNumber();
		return start.isSameFile(end) && rankMoveDistance > 0 && rankMoveDistance <= 2;
	}

	private boolean isNotAbleToMoveDoubleSquare(Position start, Position end) {
		return !isAbleToMoveDoubleSquare(start, end);
	}

	private boolean isAbleToMoveDoubleSquare(Position start, Position end) {
		return !hasMoved || (Math.abs(end.getFileNumber() - start.getFileNumber()) != 2);
	}

	@Override
	protected String getInitialCharacter() {
		return INITIAL_CHARACTER;
	}

	@Override
	public void updateHasMoved() {
		this.hasMoved = true;
	}
}
