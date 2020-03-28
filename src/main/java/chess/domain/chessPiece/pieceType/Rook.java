package chess.domain.chessPiece.pieceType;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceState.PieceState;

public class Rook extends ChessPiece {

	public static final String NAME = "R";
	public static final int SCORE = 5;

	public Rook(PieceColor pieceColor, PieceState pieceState) {
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
