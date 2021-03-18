package chess.piece.movingstrategy;

import chess.board.Point;
import chess.piece.Vector;

public interface MovingStrategy {

    Vector findMovableVector(Point source, Point destination);

    int getMoveLength();
}
