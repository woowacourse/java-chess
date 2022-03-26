package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import java.util.Objects;

public abstract class Piece {

	protected static final String MOVEMENT_ERROR = "해당 기물은 그곳으로 이동할 수 없습니다.";

	private static final String CAN_NOT_CATCH_SAME_TEAM_ERROR = "같은 팀의 기물을 잡을 수 없습니다.";

	private final String symbol;
	protected final Team team;

	public Piece(final Team team) {
		this.symbol = createSymbol(team);
		this.team = team;
	}

	public Direction getDirection(final Position source, final Position target) {
		int differenceRow = target.subtractRow(source);
		int differenceColumn = target.subtractColumn(source);
		return Direction.find(differenceRow, differenceColumn);
	}

	public String getSymbol() {
		return symbol;
	}

	public void validateCatch(final Piece targetPiece, final Direction direction) {
		if (this.team == targetPiece.team) {
			throw new IllegalArgumentException(CAN_NOT_CATCH_SAME_TEAM_ERROR);
		}
	}

	public boolean isSameTeam(final Team team) {
		return this.team == team;
	}

	protected abstract String createSymbol(final Team team);

	public abstract void validateMovement(Position source, Position target);

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
		return Objects.equals(symbol, piece.symbol) && team == piece.team;
	}

	@Override
	public int hashCode() {
		return Objects.hash(symbol, team);
	}
}
