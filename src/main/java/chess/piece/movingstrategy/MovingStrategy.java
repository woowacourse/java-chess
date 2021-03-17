package chess.piece.movingstrategy;

import chess.board.Point;
import chess.piece.Direction;

public interface MovingStrategy {

    default Direction findMovableDirection(Point source, Point destination) {
        return null;
    }

    default int getDirectionLength() {
        return 0;
    }
}
