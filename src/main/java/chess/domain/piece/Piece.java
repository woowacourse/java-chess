package chess.domain.piece;

import java.util.List;
import java.util.Objects;

import chess.domain.Position;

public abstract class Piece {
	protected static final String ILLEGAL_MOVE = "말이 움직일 수 없는 자리입니다.";

	protected List<Character> representation;
	protected final Team team;
	protected boolean isAlive;
	protected Position position;
	protected double score;

	public Piece(Position position, Team team) {
		this.team = team;
		this.position = position;
		this.isAlive = true;
	}

	public abstract void validateMove(Position destination);

	public void move(Position destination) {
		validateMove(destination);
		this.position = destination;
	}

	public void kill() {
		this.isAlive = false;
	}

	public boolean isSameTeam(Piece destinationPiece) {
		return this.team == destinationPiece.team;
	}

	public boolean isInTeam(Team team) {
		return this.team == team;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public double getScore() {
		return this.score;
	}

	public Team getTeam() {
		return team;
	}

	public String getPosition() {
		return position.toString();
	}

	public List<Character> getRepresentations() {
		return representation;
	}

	@Override
	public String toString() {
		return String.valueOf(Team.getRepresentation(this));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Piece piece = (Piece)o;
		return isAlive == piece.isAlive &&
			Double.compare(piece.score, score) == 0 &&
			Objects.equals(representation, piece.representation) &&
			team == piece.team &&
			Objects.equals(position, piece.position);
	}

	@Override
	public int hashCode() {
		return Objects.hash(representation, team, isAlive, position, score);
	}
}
