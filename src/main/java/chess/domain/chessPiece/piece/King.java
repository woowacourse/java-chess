package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movepattern.CrossPattern;
import chess.domain.movepattern.MovePattern;
import chess.domain.movepattern.StraightPattern;

import java.util.Optional;

public class King extends Piece {
	private static final int KING_SCORE = 0;
	private static final int KING_MOVABLE_RANGE = 1;

	public King(Position position, TeamStrategy teamStrategy) {
		super(position, teamStrategy);
	}

	@Override
	protected boolean isMovablePattern(MovePattern movePattern, Optional<Piece> targetPiece) {
		return (movePattern instanceof StraightPattern || movePattern instanceof CrossPattern)
				&& movePattern.getCount() == KING_MOVABLE_RANGE;
	}

	@Override
	public String getPieceName() {
		return teamStrategy.kingName();
	}

	@Override
	public double getScore() {
		return KING_SCORE;
	}
}
