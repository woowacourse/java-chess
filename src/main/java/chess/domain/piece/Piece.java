package chess.domain.piece;

import java.util.List;
import java.util.Map;

import chess.domain.Color;
import chess.domain.PieceScore;
import chess.domain.board.Position;

/**
 *    체스 기물을 의미하는 클래스입니다.
 *
 *    @author AnHyungJu, LeeHoBin
 */
public abstract class Piece {
	protected Color color;
	private String symbol;

	public Piece(Color color, String symbol) {
		this.color = color;
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

	public boolean isSameColor(Color color) {
		return this.color == color;
	}

	public abstract boolean isSameName(PieceScore pieceScore);

	public abstract List<Position> movablePositions(Position source, Map<Position, Piece> pieces);

	public boolean isPawn() {
		return false;
	}
}
