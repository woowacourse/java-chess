package chess.domain.piece;

import java.util.Optional;

import chess.domain.position.Position;
import chess.domain.direction.Direction;
import chess.domain.direction.strategy.QueenDirectionStrategy;

public class Queen extends Piece {

	private static final String INVALID_DIRECTION_QUEEN = "Queen이 갈 수 없는 방향입니다.";
	private static final double QUEEN_SCORE = 9.0;

	private static final Queen whiteQueen = new Queen(Color.WHITE, "♛");
	private static final Queen blackQueen = new Queen(Color.BLACK, "♕");

	private final String symbol;

	private Queen(Color color, String symbol) {
		super(color);
		this.symbol = symbol;
	}

	public static Queen createWhite() {
		return whiteQueen;
	}

	public static Queen createBlack() {
		return blackQueen;
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
