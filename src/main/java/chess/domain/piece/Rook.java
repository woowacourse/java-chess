package chess.domain.piece;

import java.util.Map;

import chess.domain.Color;
import chess.domain.PieceScore;
import chess.domain.board.Position;
import chess.domain.piece.strategy.BasicRepeatMoveStrategy;

public class Rook extends Piece {
	public Rook(Color color, String symbol) {
		super(color, symbol, new BasicRepeatMoveStrategy(Direction.linearDirection()));
	}

	@Override
	public boolean isSameName(PieceScore pieceScore) {
		return PieceScore.ROOK == pieceScore;
	}

	@Override
	public Path findPathByRule(Path path, Map<Position, Piece> pieces) {
		return moveStrategy.findMovablePositions(path, pieces);
	}
}
