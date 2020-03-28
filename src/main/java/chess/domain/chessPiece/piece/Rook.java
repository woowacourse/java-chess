package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movepattern.MovePattern;
import chess.domain.movepattern.StraightPattern;

public class Rook extends Piece {
	private static final int ROOK_SCORE = 5;

	public Rook(Position position, TeamStrategy teamStrategy) {
		super(position, teamStrategy);
	}

	@Override
	protected boolean isMovablePattern(MovePattern movePattern, Piece targetPiece) {
		return movePattern instanceof StraightPattern;
	}

	@Override
	public String getPieceName() {
		return teamStrategy.rookName();
	}

	@Override
	public double getScore() {
		return ROOK_SCORE;
	}
}
