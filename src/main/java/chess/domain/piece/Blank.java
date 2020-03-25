package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.movable.KingMovable;

import java.util.List;
import java.util.Set;

public class Blank extends Piece{
	public Blank() {
		super(Board.of("a1"), ".", new KingMovable(), Color.BLANK); //TODO : position, movable check
	}

	@Override
	public Set<Position> findRemovablePositions(Set<Position> positions, List<Piece> pieces) {
		return null;
	}
}
