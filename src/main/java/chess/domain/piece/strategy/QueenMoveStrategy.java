package chess.domain.piece.strategy;

import chess.domain.board.Position;
import chess.domain.exceptions.InvalidMoveException;
import chess.domain.piece.Piece;

public class QueenMoveStrategy extends NoLimitedDistanceMoveStrategy {

    @Override
    public void checkMoveType(Position source, Position target) {
        if (!isLineMove(source, target) &&
            !isDiagonalMove(source, target)) {
            throw new InvalidMoveException(Piece.UNABLE_MOVE_TYPE_MESSAGE);
        }
    }
}
