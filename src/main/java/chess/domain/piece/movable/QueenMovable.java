package chess.domain.piece.movable;

import chess.domain.position.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Set;

public class QueenMovable implements Movable {
	private Movable bishopMovable = new BishopMovable();
	private Movable rookMovable = new RookMovable();

	@Override
	public Set<Position> createMovablePositions(Position position, List<Piece> pieces, Color color) {
		Set<Position> movablePositions = bishopMovable.createMovablePositions(position, pieces, color);
		movablePositions.addAll(rookMovable.createMovablePositions(position, pieces, color));
		return movablePositions;
	}
}
