package chess.domain;

import java.util.ArrayList;
import java.util.List;

import chess.domain.chesspiece.Direction;

public class Move {

	public static List<Position> makePassablePath(List<Direction> directions, Position position) {
		List<Position> positions = new ArrayList<>();
		for (Direction dir : directions) {
			positions.addAll(makePath(dir, position));
		}
		return positions;

	}

	private static List<Position> makePath(Direction dir, Position position) {
		int x = position.getX() + dir.getX();
		int y = position.getY() + dir.getY();

		List<Position> positions = new ArrayList<>();
		while (x <= 8 && x > 0 && y > 0 && y <= 8) {
			positions.add(new Position(x, y));
			x += dir.getX();
			y += dir.getY();
		}
		return positions;
	}
}
