package domain.piece;

import java.util.Objects;

import domain.piece.position.Position;
import domain.piece.team.Team;

public abstract class Piece {
	protected Position position;
	protected Team team;
	protected String SYMBOL;

	public Piece(Position position, Team team) {
		this.position = position;
		this.team = team;
	}

	public Position getPosition() {
		return position;
	}

	public String showSymbol() {
		if (this.team == Team.WHITE) {
			return this.SYMBOL;
		}
		return this.SYMBOL.toUpperCase();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Piece piece = (Piece)o;
		return Objects.equals(position, piece.position) &&
			team == piece.team;
	}

	@Override
	public int hashCode() {
		return Objects.hash(position, team);
	}

}
