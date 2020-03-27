package chess.domain.piece;

import chess.domain.board.Position;

/**
 *    체스 기물을 의미하는 클래스입니다.
 *
 *    @author AnHyungJu, LeeHoBin
 */
public abstract class Piece {
	protected String color;
	private String symbol;

	public Piece(String color, String symbol) {
		this.color = color;
		this.symbol = symbol;
	}

	public abstract void move(Position source, Position target);

	public String getSymbol() {
		return symbol;
	}
}
