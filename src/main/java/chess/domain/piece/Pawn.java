package chess.domain.piece;

import java.util.List;
import java.util.Map;

import chess.domain.Color;
import chess.domain.Moving;
import chess.domain.PieceScore;
import chess.domain.board.Position;

/**
 *    class description
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class Pawn extends Piece {
	public Pawn(Color color, String symbol) {
		super(color, symbol);
	}

	@Override
	public boolean isSameName(PieceScore pieceScore) {
		return PieceScore.PAWN == pieceScore;
	}

	@Override
	public List<Position> movablePositions(Position source, Map<Position, Piece> pieces) {
		return Moving.goOneTimePositions(Direction.whitePawnDirection(), source, pieces);
	}

	@Override
	public boolean isPawn() {
		return true;
	}
}
