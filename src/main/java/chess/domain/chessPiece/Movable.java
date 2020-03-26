package chess.domain.chessPiece;

import chess.domain.position.Position;

public interface Movable {
    boolean canMove(Position source, Position target);

    boolean canLeap();
}
