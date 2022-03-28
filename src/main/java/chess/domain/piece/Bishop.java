package chess.domain.piece;

import chess.domain.direction.Direction;
import chess.domain.direction.strategy.BishopDirectionStrategy;
import chess.domain.direction.strategy.DirectionStrategy;
import chess.domain.position.Position;

public class Bishop extends Piece {

	private static final double BISHOP_SCORE = 3.0;

	private static final Bishop whiteBishop = new Bishop(Color.WHITE);
	private static final Bishop blackBishop = new Bishop(Color.BLACK);

	private final DirectionStrategy directionStrategy;

	private Bishop(Color color) {
		super(color);
		directionStrategy = new BishopDirectionStrategy();
	}

	public static Bishop createWhite() {
		return whiteBishop;
	}

	public static Bishop createBlack() {
		return blackBishop;
	}

	@Override
	public Direction matchDirection(Position from, Position to) {
		return directionStrategy.find(from, to);
	}

	@Override
	public boolean isPawn() {
		return false;
	}

	@Override
	public boolean isKing() {
		return false;
	}

	@Override
	public double getScore() {
		return BISHOP_SCORE;
	}
}
