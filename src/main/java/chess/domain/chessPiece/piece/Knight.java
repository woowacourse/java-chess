package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movetype.KnightType;
import chess.domain.movetype.MoveType;

public class Knight extends Piece {
	private static final double KNIGHT_SCORE = 2.5;

	private final double score = KNIGHT_SCORE;

	public Knight(Position position, TeamStrategy teamStrategy) {
		super(position, teamStrategy);
	}

	@Override
	public boolean isMovable(MoveType moveType) {
		return moveType instanceof KnightType;
	}

	@Override
	public String pieceName() {
		return teamStrategy.knightName();
	}

	@Override
	public double getScore() {
		return score;
	}
}
