package chess.domain.piece.movingstrategy;

import chess.domain.board.Point;
import chess.domain.piece.MoveVector;

public interface MovingStrategy {

    MoveVector movableVector(Point source, Point destination);

    int movingLength();

    boolean hasMovableVector(Point source, Point destination);
}
