package chess.domain.piece.strategy;

import chess.domain.board.Position;

public interface MoveStrategy {

    void move(final Position source, final Position target);
}
