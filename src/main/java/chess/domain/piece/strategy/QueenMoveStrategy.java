package chess.domain.piece.strategy;

import chess.domain.board.Position;
import chess.domain.exceptions.UnableMoveTypeException;

public class QueenMoveStrategy extends BasicMoveStrategy {

    @Override
    public void checkValidMove(Position source, Position target) {
        if (!source.isLineMove(target) &&
            !source.isDiagonalMove(target)) {
            throw new UnableMoveTypeException();
        }
    }
}
