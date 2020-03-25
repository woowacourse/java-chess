package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.board.position.Position;
import chess.domain.piece.movable.PawnMovable;

import java.util.List;
import java.util.Set;

public class Pawn extends Piece{
	public Pawn(Position position, String name, List<Direction> directions) {
		super(position, name, new PawnMovable(directions));
	}

	@Override
	public Set<Position> findRemovablePositions(Set<Position> positions, List<Piece> pieces) {
		return null;
	}
}
