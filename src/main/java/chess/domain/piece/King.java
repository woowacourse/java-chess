package chess.domain.piece;

import java.util.List;
import java.util.Map;

import chess.domain.Color;
import chess.domain.Moving;
import chess.domain.board.Position;

public class King extends Piece {
	public King(Color color, String symbol) {
		super(color, symbol);
	}

	@Override
	public List<Position> movablePositions(Position source, Map<Position, Piece> pieces) {
		return Moving.goOneTimePositions(Direction.everyDirection(), source, pieces);
	}
}
