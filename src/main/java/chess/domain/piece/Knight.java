package chess.domain.piece;

import java.util.Map;

import chess.domain.Color;
import chess.domain.PieceScore;
import chess.domain.board.Position;
import chess.domain.piece.strategy.BasicOneMoveStrategy;

public class Knight extends Piece {
	public Knight(Color color, String symbol) {
		super(color, symbol, new BasicOneMoveStrategy(Direction.knightDirection()));
	}

	@Override
	public boolean isSameName(PieceScore pieceScore) {
		return PieceScore.KNIGHT == pieceScore;
	}

	@Override
	public Path findPathByRule(Path path, Map<Position, Piece> pieces) {
		return moveStrategy.findMovablePositions(path, pieces);
	}
}
