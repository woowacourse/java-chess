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

public final class Bishop extends Piece {

	private static final Set<Direction> directions = Set.of(LEFT_UP, LEFT_DOWN, RIGHT_DOWN, RIGHT_UP);

	public Bishop(final Color color, final Position position) {
		super(color, position);
	}

	public static List<Position> getInitialBlackPosition() {
		List<Position> positions = new ArrayList<>();
		positions.add(Position.of(C, EIGHT));
		positions.add(Position.of(F, EIGHT));
		return positions;
	}

	public static List<Position> getInitialWhitePosition() {
		List<Position> positions = new ArrayList<>();
		positions.add(Position.of(C, ONE));
		positions.add(Position.of(F, ONE));
		return positions;
	}

	@Override
	public String name() {
		String name = "b";
		if (super.color().equals(WHITE)) {
			return name;
		}
		return name.toUpperCase();
	}

	@Override
	public boolean movable(final Direction direction) {
		return directions.contains(direction);
	}

	@Override
	public boolean movableByCount(final int count) {
		return true;
	}
}
