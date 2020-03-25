package chess.domain.piece.movable;

import chess.domain.board.position.Position;

import java.util.Set;

public class QueenMovable implements Movable {
	private Movable bishopMovable = new BishopMovable();
	private Movable rookMovable = new RookMovable();

	@Override
	public Set<Position> move(Position position) {
		Set<Position> movablePositions = bishopMovable.move(position);
		movablePositions.addAll(rookMovable.move(position));
		return movablePositions;
	}
}
