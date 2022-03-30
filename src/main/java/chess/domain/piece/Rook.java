package chess.domain.piece;

import chess.domain.direction.Direction;
import chess.domain.direction.strategy.DirectionStrategy;
import chess.domain.direction.strategy.RookDirectionStrategy;
import chess.domain.position.Position;

public class Rook extends Piece {

	private static final double ROOK_SCORE = 5.0;

	private static final Rook whiteRook = new Rook(Color.WHITE);
	private static final Rook blackRook = new Rook(Color.BLACK);

	private final DirectionStrategy directionStrategy;

	private Rook(Color color) {
		super(color);
		directionStrategy = new RookDirectionStrategy();
	}

	public static Rook createWhite() {
		return whiteRook;
	}

	public static Rook createBlack() {
		return blackRook;
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
		return ROOK_SCORE;
	}
}
