package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movepattern.CrossPattern;
import chess.domain.movepattern.MovePattern;
import chess.domain.movepattern.StraightPattern;

public class Queen extends Piece {
	private static final String ERROR_MESSAGE_NOT_MOVABLE = "해당 말이 갈 수 없는 칸입니다";
	private static final int QUEEN_SCORE = 9;

	public Queen(Position position, TeamStrategy teamStrategy) {
		super(position, teamStrategy);
	}

	@Override
	public void validateMovePattern(MovePattern movePattern) {
		if (movePattern instanceof StraightPattern || movePattern instanceof CrossPattern) {
			return;
		}
		throw new IllegalArgumentException(ERROR_MESSAGE_NOT_MOVABLE);
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
