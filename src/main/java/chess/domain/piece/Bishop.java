package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.movable.BishopMovable;

import java.util.List;
import java.util.Set;

public class Bishop extends Piece {
	public Bishop(Position position, String name, Color color) {
		super(position, name, new BishopMovable(), color);
	}

	@Override
	public Set<Position> findRemovablePositions(Set<Position> positions, List<Piece> pieces) {
		return null;
	}
}
