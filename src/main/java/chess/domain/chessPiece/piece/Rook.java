package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movepattern.MovePattern;
import chess.domain.movepattern.StraightPattern;

public class Rook extends Piece {
	private static final String ERROR_MESSAGE_NOT_MOVABLE = "해당 말이 갈 수 없는 칸입니다.";
	private static final int ROOK_SCORE = 5;

	private final double score = ROOK_SCORE;

	public Rook(Position position, TeamStrategy teamStrategy) {
		super(position, teamStrategy);
	}

	@Override
	public void validateMovable(MovePattern movePattern) {
		if (movePattern instanceof StraightPattern) {
			return;
		}
		throw new IllegalArgumentException(ERROR_MESSAGE_NOT_MOVABLE);
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
