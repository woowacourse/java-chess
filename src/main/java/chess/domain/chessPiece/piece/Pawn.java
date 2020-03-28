package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movepattern.Direction;
import chess.domain.movepattern.MovePattern;

public class Pawn extends Piece {
	private static final int PAWN_NORMAL_MOVE_RANGE = 1;
	private static final int PAWN_FIRST_MOVE_RANGE = 2;
	private static final double PAWN_SCORE = 0.5;
	private static final String ERROR_MESSAGE_UNSUPPORTED_METHOD = "지원하지 않는 메소드 입니다";
	private static final String ERROR_MESSAGE_NOT_MOVABLE = "해당 말이 갈 수 없는 칸입니다";

	public Pawn(Position position, TeamStrategy teamStrategy) {
		super(position, teamStrategy);
	}

	public void validateMovePattern(MovePattern movePattern, Piece targetPiece) {
		if (this.teamStrategy.isBlackTeam()) {
			blackPawnMovePattern(movePattern, targetPiece);
			return;
		}
		whitePawnMovePattern(movePattern, targetPiece);
	}

	private void blackPawnMovePattern(MovePattern movePattern, Piece targetPiece) {
		if (targetPiece != null && isMovePatternAttackOfBlackPawn(movePattern)) {
			return;
		}
		if (this.position.isPawnStartLine(this) && isMovePatternOnStartLineOfBlackPawn(movePattern)
				&& targetPiece == null) {
			return;
		}
		if (isNormalMovePatternOfBlackPawn(movePattern) && targetPiece == null) {
			return;
		}
		throw new IllegalArgumentException(ERROR_MESSAGE_NOT_MOVABLE);
	}

	private boolean isMovePatternAttackOfBlackPawn(MovePattern movePattern) {
		return movePattern.getDirection() == Direction.UP_RIGHT
				|| movePattern.getDirection() == Direction.UP_LEFT && movePattern.getCount() == PAWN_NORMAL_MOVE_RANGE;
	}

	private boolean isMovePatternOnStartLineOfBlackPawn(MovePattern movePattern) {
		return movePattern.getDirection() == Direction.UP && movePattern.getCount() <= PAWN_FIRST_MOVE_RANGE;
	}

	private boolean isNormalMovePatternOfBlackPawn(MovePattern movePattern) {
		return movePattern.getDirection() == Direction.UP && movePattern.getCount() == PAWN_NORMAL_MOVE_RANGE;
	}

	private void whitePawnMovePattern(MovePattern movePattern, Piece targetPiece) {
		if (targetPiece != null && isMovePatternAttackOfWhitePawn(movePattern)) {
			return;
		}
		if (this.position.isPawnStartLine(this) && isMovePatternOnStartLineOfWhitePawn(movePattern)
				&& targetPiece == null) {
			return;
		}
		if (isNormalMovePatternOfWhitePawn(movePattern) && targetPiece == null) {
			return;
		}
		throw new IllegalArgumentException(ERROR_MESSAGE_NOT_MOVABLE);
	}

	private boolean isMovePatternAttackOfWhitePawn(MovePattern movePattern) {
		return movePattern.getDirection() == Direction.DOWN_RIGHT
				|| movePattern.getDirection() == Direction.DOWN_LEFT && movePattern.getCount() == PAWN_NORMAL_MOVE_RANGE;
	}

	private boolean isMovePatternOnStartLineOfWhitePawn(MovePattern movePattern) {
		return movePattern.getDirection() == Direction.DOWN && movePattern.getCount() <= PAWN_FIRST_MOVE_RANGE;
	}

	private boolean isNormalMovePatternOfWhitePawn(MovePattern movePattern) {
		return movePattern.getDirection() == Direction.DOWN && movePattern.getCount() == PAWN_NORMAL_MOVE_RANGE;
	}


	@Override
	public void validateMovePattern(MovePattern movePattern) {
		throw new UnsupportedOperationException(ERROR_MESSAGE_UNSUPPORTED_METHOD);
	}

	@Override
	public String getPieceName() {
		return teamStrategy.pawnName();
	}

	@Override
	public double getScore() {
		return PAWN_SCORE;
	}

	public boolean isBlackTeam() {
		return teamStrategy instanceof BlackTeam;
	}
}
