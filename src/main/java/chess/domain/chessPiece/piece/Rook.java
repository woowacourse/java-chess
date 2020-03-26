package chess.domain.chessPiece.piece;

import chess.domain.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movefactory.MoveType;
import chess.domain.movefactory.StraightType;

public class Rook extends Piece {
	private static final int ROOK_SCORE = 5;

	private final double score = ROOK_SCORE;

	public Rook(Position position, TeamStrategy teamStrategy) {
		super(position, teamStrategy);
	}

	@Override
	public boolean isMovable(MoveType moveType) {
		return moveType instanceof StraightType;
	}

	@Override
	public String pieceName() {
		return teamStrategy.rookName();
	}

	@Override
	public double getScore() {
		return score;
	}
}
