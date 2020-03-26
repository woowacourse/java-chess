package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movepattern.KnightPattern;
import chess.domain.movepattern.MovePattern;

public class Knight extends Piece {
	private static final String ERROR_MESSAGE_NOT_MOVABLE = "해당 말이 갈 수 없는 칸입니다.";
	private static final double KNIGHT_SCORE = 2.5;

	private final double score = KNIGHT_SCORE;

	public Knight(Position position, TeamStrategy teamStrategy) {
		super(position, teamStrategy);
	}

	@Override
	public void validateMovable(MovePattern movePattern) {
		if (movePattern instanceof KnightPattern) {
			return;
		}
		throw new IllegalArgumentException(ERROR_MESSAGE_NOT_MOVABLE);
	}

	@Override
	public String pieceName() {
		return teamStrategy.knightName();
	}

	@Override
	public double getScore() {
		return score;
	}
}
