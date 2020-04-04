package chess.domain.piece;

import java.util.List;

import chess.domain.position.Position;

public abstract class Piece {
	protected final Team team;
	protected boolean hasMoved;

	public Piece(Team team) {
		this(team, false);
	}

	public Piece(Team team, boolean hasMoved) {
		this.team = team;
		this.hasMoved = hasMoved;
	}

	public String getSymbol() {
		if (team.isBlack()) {
			return getInitialCharacter();
		}
		return getInitialCharacter().toLowerCase();
	}

	public void updateHasMoved() {
		hasMoved = true;
	}

	public boolean isSameTeam(Piece piece) {
		return this.team == piece.team;
	}

	public Team getTeam() {
		return team;
	}

	public boolean isRightTeam(Team turn) {
		return team == turn;
	}

	public boolean isNotBlank() {
		return team.isNotBlank();
	}

	public boolean isKing() {
		return false;
	}

	public boolean isPawn() {
		return false;
	}

	public boolean isBlank() {
		return !team.isNotBlank();
	}

	public List<Position> findCatchModeTrace(Position from, Position to) {
		return findMoveModeTrace(from, to);
	}

	public abstract List<Position> findMoveModeTrace(Position from, Position to);

	protected abstract String getInitialCharacter();

	public abstract double getScore();
}
