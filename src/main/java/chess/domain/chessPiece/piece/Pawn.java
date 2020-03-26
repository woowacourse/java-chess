package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movepattern.Direction;
import chess.domain.movepattern.MovePattern;

public class Pawn extends Piece {
	private static final int PAWN_NORMAL_MOVE_RANGE = 1;
	private static final int PAWN_FIRST_MOVE_RANGE = 2;
	private static final String ERROR_MESSAGE_UNSUPPORTED_METHOD = "지원하지 않는 메소드 입니다";
	private static final double PAWN_SCORE = 0.5;

	private final double score = PAWN_SCORE;

	public Pawn(Position position, TeamStrategy teamStrategy) {
		super(position, teamStrategy);
	}

	public boolean isMovable(MovePattern movePattern, Piece targetPiece) {
		if (this.teamStrategy instanceof BlackTeam) {
			return blackPawnMovable(movePattern, targetPiece);
		}
		return whitePawnMovable(movePattern, targetPiece);
	}

	private boolean whitePawnMovable(MovePattern movePattern, Piece targetPiece) {
		if (targetPiece != null) {
			return movePattern.getDirection() == Direction.DOWN_RIGHT
					|| movePattern.getDirection() == Direction.DOWN_LEFT && movePattern.getCount() == PAWN_NORMAL_MOVE_RANGE;
		}
		if (this.position.isPawnStartLine(this)) {
			return movePattern.getDirection() == Direction.DOWN && movePattern.getCount() <= PAWN_FIRST_MOVE_RANGE;
		}
		return movePattern.getDirection() == Direction.DOWN && movePattern.getCount() == PAWN_NORMAL_MOVE_RANGE;
	}

	private boolean blackPawnMovable(MovePattern movePattern, Piece targetPiece) {
		if (targetPiece != null) {
			return movePattern.getDirection() == Direction.UP_RIGHT
					|| movePattern.getDirection() == Direction.UP_LEFT && movePattern.getCount() == PAWN_NORMAL_MOVE_RANGE;
		}
		if (this.position.isPawnStartLine(this)) {
			return movePattern.getDirection() == Direction.UP && movePattern.getCount() <= PAWN_FIRST_MOVE_RANGE;
		}
		return movePattern.getDirection() == Direction.UP && movePattern.getCount() == PAWN_NORMAL_MOVE_RANGE;
	}


	@Override
	public boolean isMovable(MovePattern movePattern) {
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
