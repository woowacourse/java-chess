package domain.piece;

import domain.piece.position.Direction;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class Knight extends Piece {
	private static final String SYMBOL = "n";

	public Knight(Position position, Team team) {
		super(position, team);
	}

	@Override
	protected boolean validDirection(int rowGap, int columnGap) {
		return Direction.knightDirection().stream()
			.anyMatch(direction -> direction.getColumnGap() == columnGap && direction.getRowGap() == rowGap);
	}
}
