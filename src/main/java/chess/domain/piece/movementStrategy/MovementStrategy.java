package chess.domain.piece.movementStrategy;

import chess.domain.board.Point;
import chess.domain.piece.MoveVector;

public interface MovementStrategy {

    MoveVector movableVector(Point source, Point destination);

    int movementRange();

    boolean hasMovableVector(Point source, Point destination);
}
