package chess.domain.chessPiece.pieceType;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceState.PieceState;

public class Pawn extends ChessPiece {

	public static final String NAME = "P";
	public static final int PAWN_STANDARD_SCORE_BOUND = 1;
	private static final int SCORE = 1;

	public Pawn(PieceColor pieceColor, PieceState pieceState) {
		super(pieceColor, pieceState);
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
