package chess.domain.chessPiece;

import chess.domain.position.Position;

public interface Catchable {

	boolean canCatch(final Position sourcePosition, final Position targetPosition);

}
