package chess.domain.piece;

import java.util.List;
import java.util.Map;

import chess.domain.Color;
import chess.domain.Moving;
import chess.domain.board.Position;

public class Bishop extends Piece {
	public Bishop(Color color, String symbol) {
		super(color, symbol);
	}

	@Override
	public List<Position> movablePositions(Position source, Map<Position, Piece> pieces) {
		return Moving.goManyTimesPositions(Direction.diagonalDirection(), source, pieces);
	}

}
