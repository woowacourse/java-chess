package chess.domain.piece;

import chess.domain.direction.Direction;
import chess.domain.direction.strategy.DirectionStrategy;
import chess.domain.direction.strategy.RoyalDirectionStrategy;
import chess.domain.position.Position;

public class Queen extends Piece {

	private static final double QUEEN_SCORE = 9.0;

	private static final Queen whiteQueen = new Queen(Color.WHITE);
	private static final Queen blackQueen = new Queen(Color.BLACK);

	private final DirectionStrategy directionStrategy;

	private Queen(Color color) {
		super(color);
		directionStrategy = new RoyalDirectionStrategy();
	}

	public static Queen createWhite() {
		return whiteQueen;
	}

	public static Queen createBlack() {
		return blackQueen;
	}

	@Override
	public Direction getMovableDirection(Position from, Position to) {
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
		return QUEEN_SCORE;
	}
}
