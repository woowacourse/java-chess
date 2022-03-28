package chess.domain.piece;

import chess.domain.direction.Direction;
import chess.domain.direction.strategy.BlackPawnDirectionStrategy;
import chess.domain.direction.strategy.DirectionStrategy;
import chess.domain.direction.strategy.WhitePawnDirectionStrategy;
import chess.domain.position.Position;

public class Pawn extends Piece {

	private static final String INVALID_DISTANCE_PAWN = "Pawn이 갈 수 없는 거리입니다.";
	private static final double PAWN_SCORE = 1.0;

	private static final int BLACK_PAWN_INITIAL_ROW = 7;
	private static final int WHITE_PAWN_INITIAL_ROW = 2;

	private static final int PAWN_INITIAL_DISTANCE = 2;
	private static final int PAWN_BASIC_DISTANCE = 1;

	private static final Pawn whitePawn = new Pawn(Color.WHITE, new WhitePawnDirectionStrategy());
	private static final Pawn blackPawn = new Pawn(Color.BLACK, new BlackPawnDirectionStrategy());

	private final DirectionStrategy directionStrategy;

	public Pawn(Color color, DirectionStrategy directionStrategy) {
		super(color);
		this.directionStrategy = directionStrategy;
	}

	public static Pawn createWhite() {
		return whitePawn;
	}

	public static Pawn createBlack() {
		return blackPawn;
	}

	@Override
	public Direction matchDirection(Position from, Position to) {
		Direction direction = directionStrategy.find(from, to);
		if (checkFirstMove(from, to, direction) || checkMove(from, to, direction)) {
			return direction;
		}
		throw new IllegalArgumentException(INVALID_DISTANCE_PAWN);
	}

	private boolean checkFirstMove(Position from, Position to, Direction direction) {
		if (this.color == Color.BLACK) {
			return from.isSameRow(BLACK_PAWN_INITIAL_ROW) && !direction.isDiagonal()
				&& from.canReach(to, direction.getUnitPosition(), PAWN_INITIAL_DISTANCE);
		}
		return from.isSameRow(WHITE_PAWN_INITIAL_ROW) && !direction.isDiagonal()
			&& from.canReach(to, direction.getUnitPosition(), PAWN_INITIAL_DISTANCE);
	}

	private boolean checkMove(Position from, Position to, Direction direction) {
		return from.canReach(to, direction.getUnitPosition(), PAWN_BASIC_DISTANCE);
	}

	@Override
	public boolean isPawn() {
		return true;
	}

	@Override
	public boolean isKing() {
		return false;
	}

	@Override
	public double getScore() {
		return PAWN_SCORE;
	}
}
