package chess.domain.piece;

import chess.domain.direction.Direction;
import chess.domain.direction.strategy.DirectionStrategy;
import chess.domain.direction.strategy.KnightDirectionStrategy;
import chess.domain.position.Position;

public class Knight extends Piece {

	private static final double KNIGHT_SCORE = 2.5;

	private static final Knight whiteKing = new Knight(Color.WHITE);
	private static final Knight blackKing = new Knight(Color.BLACK);

	private final DirectionStrategy directionStrategy;

	private Knight(Color color) {
		super(color);
		directionStrategy = new KnightDirectionStrategy();
	}

	public static Knight createWhite() {
		return whiteKing;
	}

	public static Knight createBlack() {
		return blackKing;
	}

	@Override
	public Direction checkMovableRange(Position from, Position to) {
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
		return KNIGHT_SCORE;
	}
}
