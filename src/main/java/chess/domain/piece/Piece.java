package chess.domain.piece;

public abstract class Piece {

	private final String symbol;
	private final Team team;

	public Piece(final Team team) {
		this.symbol = createSymbol(team);
		this.team = team;
	}

	protected abstract String createSymbol(final Team team);

	public abstract boolean isBlank();

	public String getSymbol() {
		return symbol;
	}
}
