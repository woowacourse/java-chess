package domain.piece;

public class Knight extends Piece {

	public Knight(final String team) {
		super(team);
	}

	@Override
	protected String createSymbol(final String team) {
		if (team.equals("Black")) {
			return "N";
		}
		return "n";
	}
}
