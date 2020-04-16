package chess.domain.chessPiece.pieceStrategy;

import chess.domain.position.Position;

public class KnightStrategy implements PieceStrategy {

	public static final int KNIGHT_MOVABLE_RANGE = 3;

	@Override
	public boolean canLeap() {
		return true;
	}

	@Override
	public boolean canMove(final Position sourcePosition, final Position targetPosition, final int pawnMovableRange) {
		final int chessFileGap = Math.abs(sourcePosition.calculateChessFileGapTo(targetPosition));
		final int chessRankGap = Math.abs(sourcePosition.calculateChessRankGapTo(targetPosition));

		return isNotExistOnAxis(chessFileGap, chessRankGap) && isKnightCanMoveRange(chessFileGap, chessRankGap);
	}

	private boolean isNotExistOnAxis(final int chessFileGap, final int chessRankGap) {
		return chessFileGap != 0 && chessRankGap != 0;
	}

	private boolean isKnightCanMoveRange(final int chessFileGap, final int chessRankGap) {
		return (chessFileGap + chessRankGap) == KNIGHT_MOVABLE_RANGE;
	}

	@Override
	public boolean canCatch(final Position sourcePosition, final Position targetPosition) {
		final int chessFileGap = Math.abs(sourcePosition.calculateChessFileGapTo(targetPosition));
		final int chessRankGap = Math.abs(sourcePosition.calculateChessRankGapTo(targetPosition));

		return isNotExistOnAxis(chessFileGap, chessRankGap) && isKnightCanMoveRange(chessFileGap, chessRankGap);
	}

}
