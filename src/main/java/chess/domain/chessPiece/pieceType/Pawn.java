package chess.domain.chessPiece.pieceType;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceState.PieceState;

public class Pawn extends ChessPiece {

	public static final String NAME = "P";
	private static final int SCORE = 1;

	public Pawn(PieceColor pieceColor, PieceState pieceState) {
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
