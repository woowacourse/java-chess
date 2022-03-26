package chess.domain.piece;

import java.util.Optional;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.direction.Direction;
import chess.domain.direction.strategy.BlackPawnDirectionStrategy;
import chess.domain.direction.strategy.DirectionStrategy;
import chess.domain.direction.strategy.WhitePawnDirectionStrategy;

public class Pawn extends Piece {

	public static final int BLACK_PAWN_INITIAL_ROW = 7;
	public static final int WHITE_PAWN_INITIAL_ROW = 2;

	private final String symbol;

	public Pawn(Color color, String symbol) {
		super(color);
		this.symbol = symbol;
	}

	public static Pawn createWhite() {
		return new Pawn(Color.WHITE, "♟");
	}

	public static Pawn createBlack() {
		return new Pawn(Color.BLACK, "♙");
	}

	@Override
	public boolean isPawn() {
		return true;
	}

	@Override
	public String getSymbol() {
		return this.symbol;
	}

	@Override
	public Direction matchDirection(Position from, Position to) {
		Optional<? extends Direction> findDirection = findStrategy().find(from, to);
		if (findDirection.isEmpty()) {
			throw new IllegalArgumentException();
		}

		Direction direction = findDirection.get();
		if (checkFirstMove(from, to, direction) || checkMove(from, to, direction)) {
			return direction;
		}
		throw new IllegalArgumentException();
	}

	private boolean checkFirstMove(Position from, Position to, Direction direction) {
		if (this.color == Color.BLACK) {
			return from.isSameRow(BLACK_PAWN_INITIAL_ROW) && !direction.isDiagonal()
				&& from.canReach(to, direction.getUnitPosition(), 2);
		}
		return from.isSameRow(WHITE_PAWN_INITIAL_ROW) && !direction.isDiagonal()
			&& from.canReach(to, direction.getUnitPosition(), 2);
	}

	private boolean checkMove(Position from, Position to, Direction direction) {
		return from.canReach(to, direction.getUnitPosition(), 1);
	}

	private DirectionStrategy findStrategy() {
		if (this.color == Color.WHITE) {
			return new WhitePawnDirectionStrategy();
		}
		return new BlackPawnDirectionStrategy();
	}
}
