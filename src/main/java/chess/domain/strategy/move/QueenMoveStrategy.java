package chess.domain.strategy.move;

import chess.domain.board.Board;
import chess.domain.position.Position;

public final class QueenMoveStrategy extends MoveStrategy {
    @Override
    public boolean movable(final Position source, final Position target, final Board board) {
        return new RookMoveStrategy().movable(source, target, board)
                || new BishopMoveStrategy().movable(source, target, board);
    }
}