package chess.domain.piece.strategy;

import chess.domain.board.Position;
import chess.domain.exceptions.UnableMoveTypeException;

public class BishopMoveStrategy extends BasicMoveStrategy {

    @Override
    public void checkValidMove(Position source, Position target) {
        if (!source.isDiagonalMove(target)) {
            throw new UnableMoveTypeException();
        }
    }
}
