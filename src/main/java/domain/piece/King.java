package domain.piece;

public class King extends Piece {

	public King(final String team) {
		super(team);
	}

	@Override
	protected String createSymbol(final String team) {
		if (team.equals("Black")) {
			return "K";
		}
		return "k";
	}
}
