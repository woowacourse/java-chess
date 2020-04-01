package chess.domain.chessPiece.pieceType;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.chessPiece.pieceState.PieceState;
import chess.domain.position.Position;

public class King extends ChessPiece {

	public static final String NAME = "K";
	private static final int SCORE = 0;

	public King(PieceColor pieceColor, PieceState pieceState) {
		super(pieceColor, pieceState);
	}

	public King(PieceColor pieceColor) {
		this(pieceColor, new InitialState());
	}

	@Override
	public boolean canLeap() {
		return true;
	}

	@Override
	protected boolean isMovable(final Position sourcePosition, final Position targetPosition) {
		int chessFileGap = Math.abs(sourcePosition.calculateChessFileGapTo(targetPosition));
		int chessRankGap = Math.abs(sourcePosition.calculateChessRankGapTo(targetPosition));

		return chessFileGap <= 1 && chessRankGap <= 1;
	}

	@Override
	protected boolean isCatchable(final Position sourcePosition, final Position targetPosition) {
		return isMovable(sourcePosition, targetPosition);
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
