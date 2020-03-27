package domain.piece;

import java.util.List;

import domain.board.Rank;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class Rook extends Piece {
	private static final String SYMBOL = "r";

	public Rook(Position position, Team team) {
		super(position, team);
	}

	@Override
	protected boolean validDirection(Direction direction) {
		if (Direction.linearDirection().contains(direction)) {
			return true;
		}
		throw new InvalidPositionException(InvalidPositionException.INVALID_DIRECTION);
	}

	@Override
	protected boolean validStepSize(int rowGap, int columnGap) {
		return true;
	}

	@Override
	protected boolean validateRoute(Direction direction, Position targetPosition, List<Rank> ranks) {
		if (direction.hasPieceInRoute(this.position, targetPosition, ranks)) {
			throw new InvalidPositionException(InvalidPositionException.HAS_PIECE_IN_ROUTE);
		}
		return true;
	}
}
