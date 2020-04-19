package chess.domain.chessPiece.pieceStrategy;

import chess.domain.position.Position;

public class KingStrategy implements PieceStrategy {

	@Override
	public boolean canLeap() {
		return true;
	}

	@Override
	public boolean canMove(final Position sourcePosition, final Position targetPosition, final int pawnMovableRange) {
		final int chessFileGap = Math.abs(sourcePosition.calculateChessFileGapTo(targetPosition));
		final int chessRankGap = Math.abs(sourcePosition.calculateChessRankGapTo(targetPosition));

		return chessFileGap <= 1 && chessRankGap <= 1;
	}

	@Override
	public boolean canCatch(final Position sourcePosition, final Position targetPosition) {
		final int chessFileGap = Math.abs(sourcePosition.calculateChessFileGapTo(targetPosition));
		final int chessRankGap = Math.abs(sourcePosition.calculateChessRankGapTo(targetPosition));

		return chessFileGap <= 1 && chessRankGap <= 1;
	}

}
