package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movepattern.MovePattern;
import chess.domain.movepattern.StraightPattern;

public class Rook extends Piece {
	private static final int ROOK_SCORE = 5;

	private final double score = ROOK_SCORE;

	public Rook(Position position, TeamStrategy teamStrategy) {
		super(position, teamStrategy);
	}

	@Override
	public boolean isMovable(MovePattern movePattern) {
		return movePattern instanceof StraightPattern;
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
