package chess.domain.piece;

import chess.domain.RelativePosition;
import chess.domain.Team;

public abstract class Piece {

	protected final Team team;
	protected final Movement movement;

	public Piece(final Team team, final Movement movement) {
		this.team = team;
		this.movement = movement;
	}

	public static Piece empty() {
		return new Empty();
	}

	public boolean isMobile(RelativePosition relativePosition) {
		return movement.isMobile(relativePosition);
	}

	public boolean isGivenTeam(Team team) {
		return this.team == team;
	}

	public boolean isEmpty() {
		return false;
	}

	public boolean isKnight() {
		return false;
	}

	public boolean isNormalPawn() {
		return false;
	}

	public boolean isInitialPawn() {
		return false;
	}

	public Team getTeam() {
		return team;
	}

	public abstract PieceType getType();
}
