package chess.domain.chessPiece.pieceType;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceState.State;

public class King extends ChessPiece {

	public static final String NAME = "K";
	private static final int SCORE = 0;

	public King(PieceColor pieceColor, State state) {
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
