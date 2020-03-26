package chess.piece;

import java.util.List;

import chess.position.Position;

public class Pawn extends Piece {
	private static final String INITIAL_CHARACTER = "P";

	private boolean hasMoved;

	public Pawn(Team team) {
		super(team);
		hasMoved = false;
	}

	@Override
	public List<Position> findReachablePositions(Position start, Position end) {
		if (isNotMovable(start, end) && isNotAbleToMoveDoubleSquare(start, end)) {
			throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
		}
		// 		if(폰이 못움직이는 코스 || 무효한두칸체크())  { 예외  }
		return null;
	}

	private boolean isNotMovable(Position start, Position end) {
		return false;
	}

	private boolean isNotAbleToMoveDoubleSquare(Position start, Position end) {
		return (end.getFileNumber() - start.getFileNumber() == 2) && hasMoved;
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
