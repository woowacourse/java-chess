package chess.domain.piece;

import static chess.domain.color.Color.*;
import static chess.domain.move.Direction.*;
import static chess.domain.piece.PieceType.*;
import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import chess.domain.color.Color;
import chess.domain.move.Direction;
import chess.domain.position.Position;

public final class Queen extends Piece {
	private static final String name = "q";
	private static final Set<Direction> directions = Set.of(UP, DOWN, LEFT, RIGHT, RIGHT_UP, RIGHT_DOWN, LEFT_UP,
		LEFT_DOWN);

	public Queen(final Color color, final Position position) {
		super(color, position);
	}

	public static List<Position> initialBlackPosition() {
		List<Position> positions = new ArrayList<>();
		positions.add(Position.of(D, EIGHT));
		return positions;
	}

	public static List<Position> initialWhitePosition() {
		List<Position> positions = new ArrayList<>();
		positions.add(Position.of(D, ONE));
		return positions;
	}

	@Override
	public String name() {
		if (color() == WHITE) {
			return name;
		}
		return name.toUpperCase();
	}

	@Override
	public Set<Direction> direction() {
		return directions;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean movable(final Direction direction) {
		return directions.contains(direction);
	}

	@Override
	public boolean movableByCount(final int count) {
		return true;
	}

	@Override
	public PieceType type() {
		return QUEEN;
	}
}
