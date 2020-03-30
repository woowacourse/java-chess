package chess.domain.piece;

import java.util.List;
import java.util.Map;

import chess.domain.Color;
import chess.domain.PieceScore;
import chess.domain.board.Position;

public class Rook extends Piece {
	public Rook(Color color, String symbol) {
		super(color, symbol);
	}

	@Override
	public boolean isSameName(PieceScore pieceScore) {
		return PieceScore.ROOK == pieceScore;
	}

	@Override
	public List<Position> movablePositions(Position source, Map<Position, Piece> pieces) {
		return Moving.goAndCatchManyTimesPositions(Direction.linearDirection(), source, pieces);
	}

}
