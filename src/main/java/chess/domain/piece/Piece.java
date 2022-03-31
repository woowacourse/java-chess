package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import java.util.Objects;

public abstract class Piece {

	protected static final String MOVEMENT_ERROR = "해당 기물이 움직일 수 있는 위치가 아닙니다.";

	private static final String CAN_NOT_CATCH_AllY_ERROR = "같은 팀의 기물을 잡을 수 없습니다.";

	protected final Team team;

	protected Piece(final Team team) {
		this.team = team;
	}

	public final void validateMovement(final Position source, final Position target, final Piece targetPiece) {
		validateDirection(source, target, targetPiece);
		validateCatchAlly(targetPiece);
	}

	private void validateCatchAlly(final Piece targetPiece) {
		if (isAlly(targetPiece.team)) {
			throw new IllegalArgumentException(CAN_NOT_CATCH_AllY_ERROR);
		}
	}

	public final boolean isAlly(final Team team) {
		return this.team == team;
	}

	public Direction getDirection(final Position source, final Position target) {
		int differenceRow = target.calculateRowDifference(source);
		int differenceColumn = target.calculateColumnDifference(source);
		return Direction.find(differenceRow, differenceColumn);
	}

	protected abstract void validateDirection(final Position source, final Position target, final Piece targetPiece);

	public abstract boolean isBlank();

	public abstract boolean isPawn();

	public abstract boolean isKing();

	public abstract double getScore();

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Piece piece = (Piece) o;
		return team == piece.team;
	}

	@Override
	public int hashCode() {
		return Objects.hash(team);
	}
}
