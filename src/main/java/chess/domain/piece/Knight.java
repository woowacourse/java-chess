package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.movable.KnightMovable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Knight extends Piece {
	public Knight(Position position, String name, Color color) {
		super(position, name, new KnightMovable(), color);
	}

	@Override
	public Set<Position> findRemovablePositions(Set<Position> positions, List<Piece> pieces) {
		Set<Position> removedPositions = new HashSet<>();
		for (Piece piece : pieces) {
			if (positions.contains(piece.getPosition())) {
				removedPositions.add(piece.getPosition());
			}
		}
		return removedPositions;
	}
}
