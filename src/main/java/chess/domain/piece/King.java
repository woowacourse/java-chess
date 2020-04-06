package chess.domain.piece;

import java.util.Map;

import chess.domain.Color;
import chess.domain.PieceScore;
import chess.domain.board.Position;
import chess.domain.piece.strategy.BasicOneMoveStrategy;

public class King extends Piece {
	public King(Color color, String symbol) {
		super(color, symbol, new BasicOneMoveStrategy(Direction.everyDirection()));
	}

	@Override
	public boolean isSameName(PieceScore pieceScore) {
		return PieceScore.KING == pieceScore;
	}

	@Override
	public Path findPathByRule(Path path, Map<Position, Piece> pieces) {
		return moveStrategy.findMovablePositions(path, pieces);
	}

	@Override
	public boolean isKing() {
		return true;
	}
}
