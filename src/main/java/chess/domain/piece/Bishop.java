package chess.domain.piece;

import java.util.Map;

import chess.domain.Color;
import chess.domain.PieceScore;
import chess.domain.board.Position;
import chess.domain.piece.strategy.BasicRepeatMoveStrategy;

public class Bishop extends Piece {
	public Bishop(Color color, String symbol) {
		super(color, symbol, new BasicRepeatMoveStrategy(Direction.diagonalDirection()));
	}

	@Override
	public boolean isSameName(PieceScore pieceScore) {
		return PieceScore.BISHOP == pieceScore;
	}

	@Override
	public Path findPathByRule(Path path, Map<Position, Piece> pieces) {
		return moveStrategy.findMovablePositions(path, pieces);
	}
}
