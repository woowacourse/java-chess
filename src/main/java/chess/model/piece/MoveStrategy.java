package chess.model.piece;

import chess.model.position.Position;

public interface MoveStrategy {

    boolean canMove(Position source, Position target);
}
