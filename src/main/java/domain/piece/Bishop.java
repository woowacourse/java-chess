package domain.piece;

public class Bishop extends Piece {

	public Bishop(final String team) {
		super(team);
	}

	@Override
	protected String createSymbol(final String team) {
		if (team.equals("Black")) {
			return "B";
		}
		return "b";
	}
}
