package chess.domain.piece;

import java.util.Map;

import chess.domain.Color;
import chess.domain.PieceScore;
import chess.domain.board.Position;
import chess.domain.piece.strategy.BasicRepeatMoveStrategy;

public class Queen extends Piece {
	public Queen(Color color, String symbol) {
		super(color, symbol, new BasicRepeatMoveStrategy(Direction.everyDirection()));
	}

	@Override
	public boolean isSameName(PieceScore pieceScore) {
		return PieceScore.QUEEN == pieceScore;
	}

	@Override
	public Path findPathByRule(Path path, Map<Position, Piece> pieces) {
		return moveStrategy.findMovablePositions(path, pieces);
	}
}
