package chess.piece;

import java.util.List;

import chess.position.Position;

public abstract class Piece {
	protected final Team team;

	public Piece(Team team) {
		this.team = team;
	}

	public String getSymbol() {
		if (team.isBlack()) {
			return getInitialCharacter();
		}
		return getInitialCharacter().toLowerCase();
	}

	public void updateHasMoved() {
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

	protected abstract String getInitialCharacter();

	public abstract double getScore();

	public abstract List<Position> findMoveModeTrace(Position from, Position to);

	public List<Position> findCatchModeTrace(Position from, Position to) {
		return null;
	}
}
