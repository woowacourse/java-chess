package domain.piece;

import static domain.piece.position.InvalidPositionException.*;

import java.util.Optional;

import domain.board.Board;
import domain.piece.position.Direction;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class Pawn extends Piece {
	private static final int MIN_STEP_SIZE_OF_DIAGONAL = 1;
	public static final double PAWN_SCORE_WHEN_HAS_SAME_COLUMN = -0.5;
	private static final double SCORE = 1;
	private static final String SYMBOL = "p";

	private State state;

	public Pawn(Position position, Team team) {
		super(position, team);
		state = State.START;
	}

	@Override
	protected void validateDirection(Direction direction) {
		if (Team.WHITE.equals(this.team)) {
			if (direction.isNotContain(Direction.whitePawnDirection())) {
				throw new InvalidPositionException(INVALID_DIRECTION);
			}
		} else if (direction.isNotContain(Direction.blackPawnDirection())) {
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
	public void move(Position targetPosition, Board board) {
		int rowGap = this.position.calculateRowGap(targetPosition);
		Direction direction = Direction.findDirection(this.position, targetPosition);
		Optional<Piece> piece = board.findPiece(targetPosition);
		if (Direction.diagonalDirection().contains(direction) && rowGap == MIN_STEP_SIZE_OF_DIAGONAL) {
			piece.ifPresent(targetPiece -> {
				if (targetPiece.team.equals(this.team)) {
					throw new InvalidPositionException(HAS_OUR_TEAM_AT_TARGET_POSITION);
				}
				capture(targetPiece, board);
			});
		}
		if (Direction.linearDirection().contains(direction) && piece.isPresent()) {
			throw new InvalidPositionException(HAS_PIECE_AT_TARGET_POSITION);
		}
		this.changePosition(targetPosition, board);
		this.state = State.RUN;
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
