package chess.domain.piece;

import chess.domain.Position;

public abstract class Piece {
	protected char representation;
	protected final Team team;
	protected Position position;

	public Piece(Position position, Team team) {
		this.team = team;
		this.position = position;
	}

	public abstract void validateMove(Position destination);

	public void move(Position destination) {
		validateMove(destination);
		this.position = destination;
	}

	public boolean isSamePosition(Position otherPosition) {
		return this.position.equals(otherPosition);
	}

	@Override
	public String toString() {
		return String.valueOf(team.getTeamRepresentation().apply(representation));
	}

	public boolean isSameTeam(Piece destinationPiece) {
		return this.team == destinationPiece.team;
	}
}
