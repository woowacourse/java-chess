package chess.piece;

import chess.Position;
import chess.Route;

public interface DiagonalMove {

    Route moveRightUp(Position position);

    Route moveRightDown(Position position);

    Route moveLeftUp(Position position);

    Route moveLeftDown(Position position);

    PieceMoveType getMoveType();
}
