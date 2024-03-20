package chess.model.piece;

import chess.model.Position;

public interface MoveStrategy {

    boolean canMove(Position source, Position target);
}
