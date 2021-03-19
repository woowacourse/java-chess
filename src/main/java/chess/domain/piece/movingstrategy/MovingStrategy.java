package chess.domain.piece.movingstrategy;

import chess.domain.board.Point;
import chess.domain.piece.MoveVector;

public interface MovingStrategy {

    MoveVector findMovableVector(Point source, Point destination);

    int getMoveLength();
}
