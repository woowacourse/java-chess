package chess.piece;

import chess.Position;
import chess.Route;

public interface PerpendicularMove {

    Route moveUp(Position position);

    Route moveDown(Position position);

    Route moveRight(Position position);

    Route moveLeft(Position position);

    PieceMoveType getMoveType();
}
