package chess.domain.chessPiece.pieceStrategy;

import chess.domain.position.Position;

public interface PieceStrategy {

	boolean canLeap();

	boolean canMove(final Position sourcePosition, final Position targetPosition, final int pawnMovableRange);

	boolean canCatch(final Position sourcePosition, final Position targetPosition);

}
