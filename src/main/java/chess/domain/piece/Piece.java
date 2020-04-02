package chess.domain.piece;

import java.util.Map;

import chess.domain.Color;
import chess.domain.PieceScore;
import chess.domain.board.Position;
import chess.domain.piece.strategy.MoveStrategy;

/**
 *    체스 기물을 의미하는 클래스입니다.
 *
 *    @author AnHyungJu, LeeHoBin
 */
public abstract class Piece {
	protected Color color;
	private String symbol;
	protected MoveStrategy moveStrategy;

	public Piece(Color color, String symbol, MoveStrategy moveStrategy) {
		this.color = color;
		this.symbol = symbol;
		this.moveStrategy = moveStrategy;
	}

	public String getSymbol() {
		return symbol;
	}

	public boolean isSameColor(Color color) {
		return this.color == color;
	}

	public abstract boolean isSameName(PieceScore pieceScore);

	public boolean isPawn() {
		return false;
	}

	public boolean isKing() {
		return false;
	}

	public boolean isBlank() {
		return false;
	}

	public abstract Path findPathByRule(Path path,
		Map<Position, Piece> pieces);

	public Color getColor() {
		return color;
	}
}
