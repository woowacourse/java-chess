package chess.domain.piece;

import chess.domain.piece.state.State;

/**
 *    체스 기물을 의미하는 클래스입니다.
 *
 *    @author AnHyungJu, LeeHoBin
 */
public abstract class Piece {
	private String color;
	private String symbol;
	private State state;

	public Piece(String color, String symbol) {
		this.color = color;
		// this.state = new Started();
	}

	public static boolean isPawn(Piece piece) {
		return true;
	}
}
