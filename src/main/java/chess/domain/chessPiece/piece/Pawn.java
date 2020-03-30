package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movepattern.Direction;
import chess.domain.movepattern.MovePattern;

import java.util.Optional;

public class Pawn extends Piece {
	private static final int PAWN_NORMAL_MOVE_RANGE = 1;
	private static final int PAWN_START_LINE_MOVE_RANGE = 2;
	private static final double PAWN_SCORE = 0.5;

	public Pawn(Position position, TeamStrategy teamStrategy) {
		super(position, teamStrategy);
	}

	@Override
	protected boolean isMovablePattern(MovePattern movePattern, Optional<Piece> targetPiece) {
		return targetPiece.map(piece -> isAttackMovePattern(movePattern))
				.orElseGet(() -> isNotAttackMovePattern(movePattern));
	}

	private boolean isAttackMovePattern(MovePattern movePattern) {
		Direction attackDirectionRight = Direction.DOWN_RIGHT;
		Direction attackDirectionLeft = Direction.DOWN_LEFT;
		if (teamStrategy.isBlackTeam()) {
			attackDirectionRight = Direction.UP_RIGHT;
			attackDirectionLeft = Direction.UP_LEFT;
		}

		return (movePattern.getDirection() == attackDirectionRight
				|| movePattern.getDirection() == attackDirectionLeft)
				&& movePattern.getCount() == PAWN_NORMAL_MOVE_RANGE;
	}

	private boolean isNotAttackMovePattern(MovePattern movePattern) {
		if (this.position.isPawnStartLine(this)) {
			return isValidMove(movePattern, PAWN_START_LINE_MOVE_RANGE);
		}
		return isValidMove(movePattern, PAWN_NORMAL_MOVE_RANGE);
	}

	private boolean isValidMove(MovePattern movePattern, int validRange) {
		Direction forwardDirection = Direction.DOWN;
		if (teamStrategy.isBlackTeam()) {
			forwardDirection = Direction.UP;
		}

		return movePattern.getDirection() == forwardDirection
				&& movePattern.getCount() <= validRange;
	}

	@Override
	public String getPieceName() {
		return teamStrategy.pawnName();
	}

	@Override
	public double getScore() {
		return PAWN_SCORE;
	}
}
