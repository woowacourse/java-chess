package domain.piece;

import domain.board.Board;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class Queen extends Piece {
	private static final String SYMBOL = "q";
	private static final double SCORE = 9;

	public Queen(Position position, Team team) {
		super(position, team);
	}

	@Override
	protected void validateDirection(Direction direction) {
		boolean isWrongDirection = !Direction.isDiagonalDirection(direction)
			&& !Direction.isLinearDirection(direction);
		if (isWrongDirection) {
			throw new InvalidPositionException(InvalidPositionException.INVALID_DIRECTION);
		}
	}

	@Override
	protected void validateStepSize(Position sourcePosition, Position targetPosition) {
	}

	@Override
	protected void validateRoute(Direction direction, Position targetPosition, Board board) {
		if (direction.hasPieceInRoute(this.position, targetPosition, board)) {
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
