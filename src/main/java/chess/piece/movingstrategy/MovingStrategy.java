package chess.piece.movingstrategy;

import chess.board.Point;

public interface MovingStrategy {

    default boolean canMove(Point source, Point destination) {
        return false;
    }
}
