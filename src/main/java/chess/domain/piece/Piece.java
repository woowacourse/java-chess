package chess.domain.piece;

import java.util.List;
import java.util.Objects;

import chess.domain.position.Position;

public abstract class Piece {
	protected final Team team;
	protected final String symbol;

	public Piece(Team team, String symbol) {
		this.team = Objects.requireNonNull(team);
		this.symbol = Objects.requireNonNull(symbol);
	}

	public String getSymbol() {
		if (team.isBlack()) {
			return getInitialCharacter();
		}
		return getInitialCharacter().toLowerCase();
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Piece piece = (Piece)o;
		return team == piece.team &&
			Objects.equals(symbol, piece.symbol);
	}

	@Override
	public int hashCode() {
		return Objects.hash(team, symbol);
	}

	@Override
	public String toString() {
		return "Piece{" +
			"team=" + team +
			", symbol='" + symbol + '\'' +
			'}';
	}
}
