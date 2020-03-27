package domain.piece.pawn;

import static domain.piece.position.InvalidPositionException.*;

import java.util.List;

import domain.board.Rank;
import domain.piece.Piece;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class Pawn extends Piece {
	private static final String SYMBOL = "p";
	private State state;

	public Pawn(Position position, Team team) {
		super(position, team);
		state = State.START;
	}

	@Override
	protected boolean validDirection(Direction direction) {
		if (this.team == Team.WHITE) {
			if (Direction.whitePawnDirection().contains(direction)) {
				return true;
			}
			throw new InvalidPositionException(INVALID_DIRECTION);
		}
		if (Direction.blackPawnDirection().contains(direction)) {
			return true;
		}
		throw new InvalidPositionException(INVALID_DIRECTION);
	}

	@Override
	protected boolean validStepSize(int rowGap, int columnGap) {
		if (state.getIsValidStepSize().apply(rowGap)) {
			return true;
		}
		throw new InvalidPositionException(INVALID_STEP_SIZE);
	}

	@Override
	protected boolean validateRoute(Direction direction, Position targetPosition, List<Rank> ranks) {
		if (direction.hasPieceInRoute(this.position, targetPosition, ranks)) {
			throw new InvalidPositionException(InvalidPositionException.HAS_PIECE_IN_ROUTE);
		}
		return true;
	}
}
