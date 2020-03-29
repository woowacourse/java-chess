package chess.domain.chessPiece.pieceType;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceState.PieceState;

public class King extends ChessPiece {

	public static final String NAME = "K";
	private static final int SCORE = 0;

	public King(PieceColor pieceColor, PieceState pieceState) {
		super(pieceColor, pieceState);
	}

	@Override
	public String getName() {
		return pieceColor.convertFrom(NAME);
	}

	@Override
	public double getScore() {
		return SCORE;
	}

}
