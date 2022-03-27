package chess.domain.piece;

import java.util.Optional;

import chess.domain.position.Position;
import chess.domain.direction.Direction;
import chess.domain.direction.strategy.QueenDirectionStrategy;

public class Queen extends Piece {

	public static final String INVALID_DIRECTION_QUEEN = "Queen이 갈 수 없는 방향입니다.";
	private static final double QUEEN_SCORE = 9.0;

	private final String symbol;

	private Queen(Color color, String symbol) {
		super(color);
		this.symbol = symbol;
	}

	public static Queen createWhite() {
		return new Queen(Color.WHITE, "♛");
	}

	public static Queen createBlack() {
		return new Queen(Color.BLACK, "♕");
	}

	@Override
	public String getSymbol() {
		return this.symbol;
	}

	@Override
	public Direction matchDirection(Position from, Position to) {
		Optional<? extends Direction> direction = new QueenDirectionStrategy().find(from, to);
		if (direction.isEmpty()) {
			throw new IllegalArgumentException(INVALID_DIRECTION_QUEEN);
		}
		return direction.get();
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
