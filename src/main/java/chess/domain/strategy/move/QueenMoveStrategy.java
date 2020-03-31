package chess.domain.strategy.move;

import chess.domain.board.Board;
import chess.domain.position.Position;

public class QueenMoveStrategy extends MoveStrategy {

    @Override
    public boolean checkMovement(Position source, Position target, Board board) {
        return new RookMoveStrategy().movable(source, target, board)
                || new BishopMoveStrategy().movable(source, target, board);
    }
}