package domain.piece;

import java.util.List;

import domain.board.Rank;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class Rook extends Piece {
	private static final String SYMBOL = "r";
	private static final double SCORE = 5;

	public Rook(Position position, Team team) {
		super(position, team);
	}

	@Override
	protected void validateDirection(Direction direction) {
		if(direction.isNotContain(Direction.linearDirection())){
			throw new InvalidPositionException(InvalidPositionException.INVALID_DIRECTION);
		}
	}

	@Override
	protected void validateStepSize(Position sourcePosition, Position targetPosition) {}

	@Override
	protected void validateRoute(Direction direction, Position targetPosition, List<Rank> ranks) {
		if (direction.hasPieceInRoute(this.position, targetPosition, ranks)) {
			throw new InvalidPositionException(InvalidPositionException.HAS_PIECE_IN_ROUTE);
		}
	}

	@Override
	protected String getSymbol() {
		return SYMBOL;
	}

	@Override
	public double getScore() {
		return SCORE;
	}
}
