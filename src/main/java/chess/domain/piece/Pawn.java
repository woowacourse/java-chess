package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chess.domain.Color;
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

		List<Position> positions = new ArrayList<>();

		if (color == Color.WHITE) {
			positions.addAll(Moving.goOneTimePawnPositions(Direction.whitePawnGoDirection(), source, pieces));
			positions.addAll(Moving.catchOneTimePawnPositions(Direction.whitePawnCatchDirection(), source, pieces));
		}

		if (color == Color.BLACK) {
			positions.addAll(Moving.goOneTimePawnPositions(Direction.blackPawnGoDirection(), source, pieces));
			positions.addAll(Moving.catchOneTimePawnPositions(Direction.blackPawnCatchDirection(), source, pieces));
		}

		return positions;
	}

	@Override
	public boolean isPawn() {
		return true;
	}
}
