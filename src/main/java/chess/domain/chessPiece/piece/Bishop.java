package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movepattern.CrossPattern;
import chess.domain.movepattern.MovePattern;

import java.util.Optional;

public class Bishop extends Piece {
	private static final int BISHOP_SCORE = 3;

	public Bishop(Position position, TeamStrategy teamStrategy) {
		super(position, teamStrategy);
	}

	@Override
	protected boolean isMovablePattern(MovePattern movePattern, Optional<Piece> targetPiece) {
		return movePattern instanceof CrossPattern;
	}

	@Override
	public String getPieceName() {
		return teamStrategy.bishopName();
	}

	@Override
	public double getScore() {
		return BISHOP_SCORE;
	}
}
