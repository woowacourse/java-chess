package chess.domain.piece;

import java.util.Map;

import chess.domain.Color;
import chess.domain.PieceScore;
import chess.domain.board.Position;
import chess.domain.piece.strategy.PawnMoveStrategy;

/**
 *    class description
 *
 *    @authors AnHyungJu, LeeHoBin
 */
public class Pawn extends Piece {
	public Pawn(Color color, String symbol) {
		super(color, symbol, new PawnMoveStrategy(color));
	}

	@Override
	public boolean isSameName(PieceScore pieceScore) {
		return PieceScore.PAWN == pieceScore;
	}

	@Override
	public boolean isPawn() {
		return true;
	}

	@Override
	public Path findPathByRule(Path path, Map<Position, Piece> pieces) {
		return moveStrategy.findMovablePositions(path, pieces);
	}
}
