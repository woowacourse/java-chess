package chess.domain.piece;

import java.util.Collections;
import java.util.Map;

import chess.domain.Color;
import chess.domain.PieceScore;
import chess.domain.board.Position;
import chess.domain.piece.strategy.BasicOneMoveStrategy;

public class Blank extends Piece {
	private static final Blank blank = new Blank(Color.NONE, "");

	public Blank(Color color, String symbol) {
		super(color, symbol, new BasicOneMoveStrategy(Collections.EMPTY_LIST));
	}

	public static Blank of() {
		return blank;
	}

	@Override
	public boolean isBlank() {
		return true;
	}

	@Override
	public Path findPathByRule(Path path, Map<Position, Piece> pieces) {
		return null;
	}

	@Override
	public boolean isSameName(PieceScore pieceScore) {
		return false;
	}
}
