package chess.domain.chessPiece;

import chess.domain.position.Position;

public interface Movable {

	boolean canLeap();

	boolean canMove(final Position sourcePosition, final Position targetPosition);

}
