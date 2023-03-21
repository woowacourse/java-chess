package chess.domain.piece;

import chess.domain.RelativePosition;
import chess.domain.Team;

public abstract class Piece {

	protected final Team team;
	protected final Movement movement;
	private final PieceType pieceType;

	public Piece(final Team team, final Movement movement, final PieceType pieceType) {
		this.team = team;
		this.movement = movement;
		this.pieceType = pieceType;
	}

	public boolean isGivenTeam(Team team) {
		return this.team == team;
	}

	public boolean isMobile(RelativePosition relativePosition) {
		return movement.isMobile(relativePosition);
	}

	public boolean isGivenType(PieceType pieceType) {
		return this.pieceType == pieceType;
	}

	public PieceType getPieceType() {
		return pieceType;
	}
}
