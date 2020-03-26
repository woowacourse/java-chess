package domain.piece;

import domain.piece.position.Position;
import domain.piece.team.Team;

public class Bishop extends Piece {
	private static final String SYMBOL = "b";

	public Bishop(Position position, Team team) {
		super(position, team);
	}

	@Override
	protected boolean validDirection(int rowGap, int columnGap) {
		int absRowGap = Math.abs(rowGap);
		int absColumnGap = Math.abs(columnGap);

		return (absColumnGap == absRowGap) && (absColumnGap > 0);
	}
}
