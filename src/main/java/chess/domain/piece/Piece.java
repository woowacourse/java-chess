package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.state.State;

/**
 *    체스 기물을 의미하는 클래스입니다.
 *
 *    @author AnHyungJu, LeeHoBin
 */
public abstract class Piece {
	protected String color;
	private String symbol;
	private State state;

	public Piece(String color, String symbol) {
		this.color = color;
		this.symbol = symbol;
		// this.state = new Started();
	}

	public abstract void move(Position source, Position target);

	public static boolean isPawn(Piece piece) {
		return true;
	}

	public String getSymbol() {
		return symbol;
	}
}
