package chess.domain.chessPiece.pieceType;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceState.State;

public class Queen extends ChessPiece {

	public static final String NAME = "Q";
	private static final int SCORE = 9;

	public Queen(PieceColor pieceColor, State state) {
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
