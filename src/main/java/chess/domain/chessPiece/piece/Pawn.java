package chess.domain.chessPiece.piece;

import chess.domain.Position;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movefactory.Direction;
import chess.domain.movefactory.MoveType;

public class Pawn extends Piece {
	private static final int PAWN_NORMAL_MOVE_RANGE = 1;
	private static final int PAWN_FIRST_MOVE_RANGE = 2;
	private static final String ERROR_MESSAGE_UNSUPPORTED_METHOD = "지원하지 않는 메소드 입니다";
	private static final double PAWN_SCORE = 0.5;

	private final double score = PAWN_SCORE;

	public Pawn(Position position, TeamStrategy teamStrategy) {
		super(position, teamStrategy);
	}

	public boolean isMovable(MoveType moveType, Piece targetPiece) {
		if (this.teamStrategy instanceof BlackTeam) {
			return blackPawnMovable(moveType, targetPiece);
		}
		return whitePawnMovable(moveType, targetPiece);
	}

	private boolean whitePawnMovable(MoveType moveType, Piece targetPiece) {
		if (targetPiece != null) {
			return moveType.getDirection() == Direction.DOWN_RIGHT
					|| moveType.getDirection() == Direction.DOWN_LEFT && moveType.getCount() == PAWN_NORMAL_MOVE_RANGE;
		}
		if (this.position.isPawnStartLine(this)) {
			return moveType.getDirection() == Direction.DOWN && moveType.getCount() <= PAWN_FIRST_MOVE_RANGE;
		}
		return moveType.getDirection() == Direction.DOWN && moveType.getCount() == PAWN_NORMAL_MOVE_RANGE;
	}

	private boolean blackPawnMovable(MoveType moveType, Piece targetPiece) {
		if (targetPiece != null) {
			return moveType.getDirection() == Direction.UP_RIGHT
					|| moveType.getDirection() == Direction.UP_LEFT && moveType.getCount() == PAWN_NORMAL_MOVE_RANGE;
		}
		if (this.position.isPawnStartLine(this)) {
			return moveType.getDirection() == Direction.UP && moveType.getCount() <= PAWN_FIRST_MOVE_RANGE;
		}
		return moveType.getDirection() == Direction.UP && moveType.getCount() == PAWN_NORMAL_MOVE_RANGE;
	}


	@Override
	public boolean isMovable(MoveType moveType) {
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
