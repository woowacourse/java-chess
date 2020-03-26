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
	private static final String ERROR_MESSAGE_NOT_MOVABLE = "해당 말이 갈 수 없는 칸입니다.";

	private final double score = PAWN_SCORE;

	public Pawn(Position position, TeamStrategy teamStrategy) {
		super(position, teamStrategy);
	}

	public void validateMovable(MovePattern movePattern, Piece targetPiece) {
		if (this.teamStrategy.isBlackTeam()) {
			blackPawnMovable(movePattern, targetPiece);
			return;
		}
		whitePawnMovable(movePattern, targetPiece);
	}

	private void blackPawnMovable(MovePattern movePattern, Piece targetPiece) {
		if (targetPiece != null && isBlackPawnAttackMovePattern(movePattern)) {
			return;
		}
		if (this.position.isPawnStartLine(this) && isBlackPawnOnStartLineMovePattern(movePattern)
				&& targetPiece == null) {
			return;
		}
		if (isBlackPawnNormalMovePattern(movePattern) && targetPiece == null) {
			return;
		}
		throw new IllegalArgumentException(ERROR_MESSAGE_NOT_MOVABLE);
	}

	private boolean isBlackPawnAttackMovePattern(MovePattern movePattern) {
		return movePattern.getDirection() == Direction.UP_RIGHT
				|| movePattern.getDirection() == Direction.UP_LEFT && movePattern.getCount() == PAWN_NORMAL_MOVE_RANGE;
	}

	private boolean isBlackPawnOnStartLineMovePattern(MovePattern movePattern) {
		return movePattern.getDirection() == Direction.UP && movePattern.getCount() <= PAWN_FIRST_MOVE_RANGE;
	}

	private boolean isBlackPawnNormalMovePattern(MovePattern movePattern) {
		return movePattern.getDirection() == Direction.UP && movePattern.getCount() == PAWN_NORMAL_MOVE_RANGE;
	}

	private void whitePawnMovable(MovePattern movePattern, Piece targetPiece) {
		if (targetPiece != null && isWhitePawnAttackMovePattern(movePattern)) {
			return;
		}
		if (this.position.isPawnStartLine(this) && isWhitePawnOnStartLineMovePattern(movePattern)
				&& targetPiece == null) {
			return;
		}
		if (isWhitePawnNormalMovePattern(movePattern) && targetPiece == null) {
			return;
		}
		throw new IllegalArgumentException(ERROR_MESSAGE_NOT_MOVABLE);
	}

	private boolean isWhitePawnAttackMovePattern(MovePattern movePattern) {
		return movePattern.getDirection() == Direction.DOWN_RIGHT
				|| movePattern.getDirection() == Direction.DOWN_LEFT && movePattern.getCount() == PAWN_NORMAL_MOVE_RANGE;
	}

	private boolean isWhitePawnOnStartLineMovePattern(MovePattern movePattern) {
		return movePattern.getDirection() == Direction.DOWN && movePattern.getCount() <= PAWN_FIRST_MOVE_RANGE;
	}

	private boolean isWhitePawnNormalMovePattern(MovePattern movePattern) {
		return movePattern.getDirection() == Direction.DOWN && movePattern.getCount() == PAWN_NORMAL_MOVE_RANGE;
	}


	@Override
	public void validateMovable(MovePattern movePattern) {
		throw new UnsupportedOperationException(ERROR_MESSAGE_UNSUPPORTED_METHOD);
	}

	@Override
	public String pieceName() {
		return teamStrategy.pawnName();
	}

	@Override
	public double getScore() {
		return score;
	}

	public boolean isBlackTeam() {
		return teamStrategy instanceof BlackTeam;
	}
}
