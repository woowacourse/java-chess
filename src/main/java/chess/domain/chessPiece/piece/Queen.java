package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movepattern.CrossPattern;
import chess.domain.movepattern.MovePattern;
import chess.domain.movepattern.StraightPattern;

public class Queen extends Piece {
	private static final int QUEEN_SCORE = 9;

	public Queen(Position position, TeamStrategy teamStrategy) {
		super(position, teamStrategy);
	}

	@Override
	protected boolean isMovablePattern(MovePattern movePattern, Piece targetPiece) {
		return movePattern instanceof StraightPattern || movePattern instanceof CrossPattern;
	}

	@Override
	public String getPieceName() {
		return teamStrategy.queenName();
	}

	@Override
	public double getScore() {
		return QUEEN_SCORE;
	}
}
