package chess.domain.piece;

public class King extends Piece {

	public King(final Team team) {
		super(team);
	}

	@Override
	protected String createSymbol(final Team team) {
		if (team.isBlack()) {
			return "K";
		}
		return "k";
	}

	@Override
	public boolean isBlank() {
		return false;
	}
}
