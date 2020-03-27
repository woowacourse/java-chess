package chess.domain.chessPiece;

import chess.domain.position.Position;

public interface Catchable {

	boolean canCatch(Position sourcePosition, Position targetPosition);

}
