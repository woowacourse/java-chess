package domain.piece;

public class Queen extends Piece {

	public Queen(final String team) {
		super(team);
	}

	@Override
	protected String createSymbol(final String team) {
		if (team.equals("Black")) {
			return "Q";
		}
		return "q";
	}
}
