package chess.domain.piece.strategy;

import chess.domain.postion.Position;

public interface MoveStrategy {

    void isMovable(final Position source, final Position target);
}
