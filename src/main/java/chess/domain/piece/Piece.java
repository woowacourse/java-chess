package chess.domain.piece;

import java.util.Objects;

public abstract class Piece {

	private final Symbol symbol;
	private final Team team;

	public Piece(final Symbol symbol, final Team team) {
		this.symbol = symbol;
		this.team = team;
	}

	public String getSymbol() {
		if (team.equals(Team.BLACK)) {
			return symbol.getBlack();
		}
		return symbol.getWhite();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Piece piece = (Piece)o;
		return symbol == piece.symbol && team == piece.team;
	}

	@Override
	public int hashCode() {
		return Objects.hash(symbol, team);
	}
}
