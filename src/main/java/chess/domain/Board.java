package chess.domain;

import java.util.*;

public class Board {
	private static final int BOUND_OF_OBSTACLES = 0;
	private final Map<Position, Square> map;

	public Board(final Map<Position, Square> map) {
		this.map = new TreeMap<>(map);
	}

	public boolean isSameColor(final Position origin, Piece.Color color) {
		return map.get(origin).isSameColor(color);
	}

	public boolean action(final Position origin, final Position target) {
		if (hasObstacle(origin, target)) {
			return false;
		}
		Square originSquare = map.get(origin);
		Square targetSquare = map.get(target);

		if (targetSquare.isEmpty()) {
			return isValidMove(originSquare, targetSquare);
		}
		return isValidAttack(originSquare, targetSquare);
	}

	public boolean isValidAttack(final Square origin, final Square target) {
		if (!origin.isValidAttack(target)) {
			return false;
		}
		origin.attackPiece(target);
		return true;
	}

	public boolean isValidMove(final Square origin, final Square target) {
		if (!origin.isValidMove(target)) {
			return false;
		}
		return origin.movePiece(target);
	}

	private boolean hasObstacle(final Position origin, final Position target) {
		long test = origin.findRoutes(target).stream()
				.filter(route -> !map.get(route).isEmpty())
				.count();
		return (test > BOUND_OF_OBSTACLES);
	}

	public List<Square> values() {
		return new ArrayList<>(map.values());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Board board = (Board) o;
		return Objects.equals(map, board.map);
	}

	@Override
	public int hashCode() {
		return Objects.hash(map);
	}
}
