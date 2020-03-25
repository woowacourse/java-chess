package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.movable.QueenMovable;

import java.util.List;
import java.util.Set;

public class Queen extends Piece {
	public Queen(Position position, String name, Color color) {
		super(position, name, new QueenMovable(), color);
	}

	@Override
	public Set<Position> findRemovablePositions(Set<Position> positions, List<Piece> pieces) {
		return null;
	}
}
