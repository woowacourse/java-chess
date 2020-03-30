package chess.piece;

import java.util.List;

import chess.position.Position;

public class Bishop extends Piece {
	public Bishop(Team team) {
		super(team, "B");
	}

	@Override
	public boolean isInvalidMovementWithoutConsideringOtherPieces(Position source, Position target) {
		return source.isNotDiagonal(target);
	}

	@Override
	public List<Position> movePathExceptSourceAndTarget(Position start, Position end) {
		return Position.findDiagonalTrace(start, end);
	}
}
