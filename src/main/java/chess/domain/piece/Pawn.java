package chess.domain.piece;

import static chess.domain.color.Color.*;
import static chess.domain.move.Direction.*;
import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import chess.domain.color.Color;
import chess.domain.move.Direction;
import chess.domain.position.Position;

public final class Pawn extends Piece {
	private static final String name = "p";

	private boolean isFirst = true;

	public Pawn(final Color color, final Position position) {
		super(color, position);
	}

	public static List<Position> initialBlackPosition() {
		List<Position> positions = new ArrayList<>();
		positions.add(Position.of(A, SEVEN));
		positions.add(Position.of(B, SEVEN));
		positions.add(Position.of(C, SEVEN));
		positions.add(Position.of(D, SEVEN));
		positions.add(Position.of(E, SEVEN));
		positions.add(Position.of(F, SEVEN));
		positions.add(Position.of(G, SEVEN));
		positions.add(Position.of(H, SEVEN));
		return positions;
	}

	public static List<Position> initialWhitePosition() {
		List<Position> positions = new ArrayList<>();
		positions.add(Position.of(A, TWO));
		positions.add(Position.of(B, TWO));
		positions.add(Position.of(C, TWO));
		positions.add(Position.of(D, TWO));
		positions.add(Position.of(E, TWO));
		positions.add(Position.of(F, TWO));
		positions.add(Position.of(G, TWO));
		positions.add(Position.of(H, TWO));
		return positions;
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
		if (super.color().equals(WHITE)) {
			return ofWhitePawn();
		}
		return ofBlackPawn();
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
		if (isFirst) {
			isFirst = false;
			return count <= 1;
		}
		return count == 0;
	}
}
