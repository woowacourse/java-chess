package chess.domain.piece.movingstrategy;

import chess.domain.board.Point;
import chess.domain.piece.Vector;

public interface MovingStrategy {

    Vector findMovableVector(Point source, Point destination);

    int getMoveLength();
}
