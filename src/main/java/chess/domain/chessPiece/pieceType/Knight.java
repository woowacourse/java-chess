package chess.domain.chessPiece.pieceType;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.chessPiece.pieceState.PieceState;
import chess.domain.position.Position;

public class Knight extends ChessPiece {

	public static final int KNIGHT_MOVABLE_RANGE = 3;
	public static final String NAME = "N";
	private static final double SCORE = 2.5;

	public Knight(PieceColor pieceColor, PieceState pieceState) {
		super(pieceColor, pieceState);
	}

	public Knight(final PieceColor pieceColor) {
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

		return isNotExistOnAxis(chessFileGap, chessRankGap) && isKnightCanMoveRange(chessFileGap, chessRankGap);
	}

	private boolean isNotExistOnAxis(int chessFileGap, int chessRankGap) {
		return chessFileGap != 0 && chessRankGap != 0;
	}

	private boolean isKnightCanMoveRange(int chessFileGap, int chessRankGap) {
		return (chessFileGap + chessRankGap) == KNIGHT_MOVABLE_RANGE;
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
