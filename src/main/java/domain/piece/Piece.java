package domain.piece;

public abstract class Piece {

	private final String symbol;
	private final String team;

	public Piece(final String team) {
		this.symbol = createSymbol(team);
		this.team = team;
	}

	protected abstract String createSymbol(final String team);

	public String getSymbol() {
		return symbol;
	}
}
