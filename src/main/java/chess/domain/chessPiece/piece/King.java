package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movepattern.CrossPattern;
import chess.domain.movepattern.MovePattern;
import chess.domain.movepattern.StraightPattern;

public class King extends Piece {
	private static final int KING_SCORE = 0;

	private final double score = KING_SCORE;

	public King(Position position, TeamStrategy teamStrategy) {
		super(position, teamStrategy);
	}

	@Override
	public boolean isMovable(MovePattern movePattern) {
		return (movePattern instanceof StraightPattern || movePattern instanceof CrossPattern) && movePattern.getCount() == 1;
	}

	@Override
	public String pieceName() {
		return teamStrategy.kingName();
	}

	@Override
	public double getScore() {
		return score;
	}
}
