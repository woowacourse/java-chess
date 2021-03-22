package chess.domain.piece.strategy;

import chess.domain.position.Position;

public class KingStrategy implements MoveStrategy {

    @Override
    public boolean canMove(Position from, Position to) {
        return (from.isStraight(to) || from.isDiagonal(to)) &&
            (from.diagonalDistance(to) == 2 || from.diagonalDistance(to) == 1);
    }
}
