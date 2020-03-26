package domain.piece;

import java.util.List;

import domain.piece.position.Direction;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class Pawn extends Piece {
	private static final String SYMBOL = "p";

	public Pawn(Position position, Team team) {
		super(position, team);
	}

	private boolean validateDirectionByTeam(int rowGap, int columnGap, List<Direction> directions) {
		return directions.stream()
			.anyMatch(direction -> direction.getColumnGap() == columnGap && direction.getRowGap() == rowGap);
	}

	@Override
	protected boolean validDirection(int rowGap, int columnGap) {
		if (this.team == Team.WHITE) {
			return validateDirectionByTeam(rowGap, columnGap, Direction.whitePawnDirection());
		}
		return validateDirectionByTeam(rowGap, columnGap, Direction.blackPawnDirection());
	}
}
