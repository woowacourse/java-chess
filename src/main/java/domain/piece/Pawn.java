package domain.piece;

public class Pawn extends Piece {

	public Pawn(final String team) {
		super(team);
	}

	@Override
	protected String createSymbol(final String team) {
		if (team.equals("Black")) {
			return "P";
		}
		return "p";
	}
}
