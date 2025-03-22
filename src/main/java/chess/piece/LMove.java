package chess.piece;

import chess.Position;
import chess.Route;

public interface LMove {

    Route moveUpRightUp(Position position);

    Route moveUpLeftUp(Position position);

    Route moveRightUpRight(Position position);

    Route moveRightDownRight(Position position);

    Route moveDownRightDown(Position position);

    Route moveDownLeftDown(Position position);

    Route moveLeftUpLeft(Position position);

    Route moveLeftDownLeft(Position position);

    PieceMoveType getMoveType();
}
