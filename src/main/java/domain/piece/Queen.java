package domain.piece;

import domain.piece.position.Position;
import domain.piece.team.Team;

public class Queen extends Piece {
	private static final String SYMBOL = "q";

	public Queen(Position position, Team team) {
		super(position, team);
	}

	@Override
	boolean validDirection(int rowGap, int columnGap) {
		int absRowGap = Math.abs(rowGap);
		int absColumnGap = Math.abs(columnGap);
		return (((absRowGap > 0 && absRowGap < 8) && absColumnGap == 0) || ((absColumnGap > 0 && absColumnGap < 8)
			&& absRowGap == 0)) || (absColumnGap == absRowGap) && (absColumnGap > 0);
	}
}
