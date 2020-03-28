package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movepattern.KnightPattern;
import chess.domain.movepattern.MovePattern;

public class Knight extends Piece {
	private static final double KNIGHT_SCORE = 2.5;

	public Knight(Position position, TeamStrategy teamStrategy) {
		super(position, teamStrategy);
	}

	@Override
	protected boolean isMovablePattern(MovePattern movePattern, Piece targetPiece) {
		return movePattern instanceof KnightPattern;
	}

	@Override
	public String getPieceName() {
		return teamStrategy.knightName();
	}

	@Override
	public double getScore() {
		return KNIGHT_SCORE;
	}
}
