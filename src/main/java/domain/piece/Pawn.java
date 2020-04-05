package domain.piece;

import static domain.piece.position.InvalidPositionException.*;

import java.util.Optional;

import domain.board.Board;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class Pawn extends Piece {
	public static final double PAWN_SCORE_WHEN_HAS_SAME_COLUMN = -0.5;
	private static final int MIN_STEP_SIZE_OF_DIAGONAL = 1;
	private static final double SCORE = 1;
	private static final String SYMBOL = "p";

	private State state;

	public Pawn(Position position, Team team) {
		super(position, team);
		state = State.START;
	}

	@Override
	public void move(Position targetPosition, Team turn, Board board) {
		validateMovement(targetPosition, turn, board);
		int rowGap = this.position.calculateRowGap(targetPosition);
		Direction direction = Direction.findDirection(this.position, targetPosition);
		Optional<Piece> piece = board.findPiece(targetPosition);
		if (Direction.isDiagonalDirection(direction) && rowGap == MIN_STEP_SIZE_OF_DIAGONAL) {
			piece.ifPresent(targetPiece -> {
				if (targetPiece.team.equals(this.team)) {
					throw new InvalidPositionException(HAS_OUR_TEAM_AT_TARGET_POSITION);
				}
				capture(targetPiece, board);
			});
		}
		if (Direction.isLinearDirection(direction) && piece.isPresent()) {
			throw new InvalidPositionException(HAS_PIECE_AT_TARGET_POSITION);
		}
		this.changePosition(targetPosition, board);
		this.state = State.RUN;
	}

	@Override
	protected void validateDirection(Direction direction) {
		boolean isWrongDirection;
		if (Team.isWhite(team)) {
			isWrongDirection = !Direction.isWhitePawnDirection(direction);
		} else {
			isWrongDirection = !Direction.isBlackPawnDirection(direction);
		}
		if (isWrongDirection) {
			throw new InvalidPositionException(INVALID_DIRECTION);
		}
	}

	@Override
	protected void validateStepSize(Position sourcePosition, Position targetPosition) {
		int rowGap = this.position.calculateRowGap(targetPosition);

		int absStepSize = Math.abs(rowGap);
		if (!state.getIsValidStepSize().apply(absStepSize)) {
			throw new InvalidPositionException(INVALID_STEP_SIZE);
		}
	}

	@Override
	protected void validateRoute(Direction direction, Position targetPosition, Board board) {
		if (direction.hasPieceInRoute(this.position, targetPosition, board)) {
			throw new InvalidPositionException(HAS_PIECE_IN_ROUTE);
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
