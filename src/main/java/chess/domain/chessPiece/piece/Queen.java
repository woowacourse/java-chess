package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movepattern.CrossPattern;
import chess.domain.movepattern.MovePattern;
import chess.domain.movepattern.StraightPattern;

public class Queen extends Piece {
	private static final int QUEEN_SCORE = 9;

	private final double score = QUEEN_SCORE;

	public Queen(Position position, TeamStrategy teamStrategy) {
		super(position, teamStrategy);
	}

	@Override
	public boolean isMovable(MovePattern movePattern) {
		return movePattern instanceof StraightPattern || movePattern instanceof CrossPattern;
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
