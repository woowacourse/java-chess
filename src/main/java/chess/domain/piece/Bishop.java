package chess.domain.piece;

public class Bishop extends Piece {

	public Bishop(final Team team) {
		super(team);
	}

	@Override
	protected String createSymbol(final Team team) {
		if (team.isBlack()) {
			return "B";
		}
		return "b";
	}

	@Override
	public boolean isBlank() {
		return false;
	}
}
