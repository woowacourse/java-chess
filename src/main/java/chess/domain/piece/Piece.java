package chess.domain.piece;

import java.util.List;

import chess.domain.position.Position;

public abstract class Piece {
	protected Position position;
	protected final Name name;
	protected final Team team;

	public Piece(Position position, Name name, Team team) {
		this.position = position;
		this.name = name;
		this.team = team;
	}

	public void moveTo(Position destination) {
		position = destination;
	}

	public abstract boolean canNotMoveTo(Piece that);

	protected abstract List<Position> createMovableArea();

	public abstract boolean isObstacle();

	public abstract boolean hasToAlive();

	public abstract boolean isPenaltyApplier();

	public boolean isSameTeam(Team team) {
		return this.team == team;
	}

	public boolean isNotSameTeam(Team team) {
		return this.team != team;
	}

	public Position getPosition() {
		return position;
	}

	public Name getName() {
		return name;
	}

	public Team getTeam() {
		return team;
	}

	public String getSymbol() {
		if (Team.WHITE == team) {
			return name.getWhiteSymbol();
		}
		return name.getBlackSymbol();
	}
}
