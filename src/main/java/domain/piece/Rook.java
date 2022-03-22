package domain.piece;

public class Rook extends Piece {

	public Rook(final String team) {
		super(team);
	}

	@Override
	protected String createSymbol(final String team) {
		if (team.equals("Black")) {
			return "R";
		}
		return "r";
	}

}
