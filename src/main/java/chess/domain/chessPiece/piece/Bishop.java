package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movetype.CrossType;
import chess.domain.movetype.MoveType;

public class Bishop extends Piece {
	private static final int BISHOP_SCORE = 3;

	private final double score = BISHOP_SCORE;

	public Bishop(Position position, TeamStrategy teamStrategy) {
		super(position, teamStrategy);
	}

	@Override
	public boolean isMovable(MoveType moveType) {
		return moveType instanceof CrossType;
	}

	@Override
	public String pieceName() {
		return teamStrategy.bishopName();
	}

	@Override
	public double getScore() {
		return score;
	}
}
