package chess.domain.chessPiece.pieceType;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceState.State;

public class Knight extends ChessPiece {

	public static final String NAME = "N";
	private static final double SCORE = 2.5;

	public Knight(PieceColor pieceColor, State state) {
		super(pieceColor, state);
	}

	@Override
	public String getName() {
		return pieceColor.convertName(NAME);
	}

	@Override
	public double getScore() {
		return SCORE;
	}

}
