package chess.domain.move;

import chess.domain.position.Position;

public class QueenMoveStrategy extends MoveStrategy {

    @Override
    public boolean movable(Position source, Position target) {
        return new RookMoveStrategy().movable(source, target)
                && new BishopMoveStrategy().movable(source, target);
    }
}