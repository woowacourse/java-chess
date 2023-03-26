package chess.domain.piece;

import static chess.domain.color.Color.*;
import static chess.domain.move.Direction.*;
import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;

import java.util.List;
import java.util.Set;

import chess.domain.color.Color;
import chess.domain.move.Direction;
import chess.domain.position.Position;

public final class Pawn extends Piece {
	private static final String name = "p";
	private static final List<Position> blackPositions = List.of(
		Position.of(A, SEVEN), Position.of(B, SEVEN),
		Position.of(C, SEVEN), Position.of(D, SEVEN),
		Position.of(E, SEVEN), Position.of(F, SEVEN),
		Position.of(G, SEVEN), Position.of(H, SEVEN));
	private static final List<Position> whitePositions = List.of(
		Position.of(A, TWO), Position.of(B, TWO),
		Position.of(C, TWO), Position.of(D, TWO),
		Position.of(E, TWO), Position.of(F, TWO),
		Position.of(G, TWO), Position.of(H, TWO));

	public Pawn(final Color color, final Position position) {
		super(color, position);
	}

	public static List<Position> initialBlackPosition() {
		return blackPositions;
	}

	public static List<Position> initialWhitePosition() {
		return whitePositions;
	}

	@Override
	public String name() {
		if (super.color().equals(WHITE)) {
			return name;
		}
		return name.toUpperCase();
	}

	@Override
	public Set<Direction> direction() {
		final Set<Direction> whiteDirections = Set.of(UP, LEFT_UP, RIGHT_UP);
		final Set<Direction> blackDirections = Set.of(DOWN, RIGHT_DOWN, LEFT_DOWN);
		if (super.color().equals(WHITE)) {
			return whiteDirections;
		}
		return blackDirections;
	}

	@Override
	public boolean movable(final Direction direction) {
		if (name().equals(name().toUpperCase())) {
			return DOWN.equals(direction);
		}
		return UP.equals(direction);
	}

	@Override
	public boolean movableByCount(final int count) {
		if (color() == WHITE) {
			return isFirstMove(whitePositions, count);
		}
		return isFirstMove(blackPositions, 0);
	}

	private boolean isFirstMove(final List<Position> positions, final int count) {
		if (positions.contains(position())) {
			return count <= 1;
		}
		return count == 0;
	}
}
