package chess.domain.piece;

import java.util.Map;

import chess.domain.Color;
import chess.domain.PieceScore;
import chess.domain.board.Position;
import chess.domain.piece.strategy.BlankMoveStrategy;

public class Blank extends Piece {
	private static final Blank blank = new Blank(Color.NONE, "blank");

	public Blank(Color color, String symbol) {
		super(color, symbol, new BlankMoveStrategy());
	}

	public static Blank getInstance() {
		return blank;
	}

	@Override
	public boolean isBlank() {
		return true;
	}

	@Override
	public Path findPathByRule(Path path, Map<Position, Piece> pieces) {
		return moveStrategy.findMovablePositions(path, pieces);
	}

	@Override
	public boolean isSameName(PieceScore pieceScore) {
		return false;
	}
}
