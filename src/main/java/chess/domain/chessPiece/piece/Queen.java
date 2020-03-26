package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movetype.CrossType;
import chess.domain.movetype.MoveType;
import chess.domain.movetype.StraightType;

public class Queen extends Piece {
	private static final int QUEEN_SCORE = 9;

	private final double score = QUEEN_SCORE;

	public Queen(Position position, TeamStrategy teamStrategy) {
		super(position, teamStrategy);
	}

	@Override
	public boolean isMovable(MoveType moveType) {
		return moveType instanceof StraightType || moveType instanceof CrossType;
	}

	@Override
	public String pieceName() {
		return teamStrategy.queenName();
	}

	@Override
	public double getScore() {
		return score;
	}
}
