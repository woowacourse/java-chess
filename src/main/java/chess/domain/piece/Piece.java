package chess.domain.piece;

import chess.domain.Position;

public interface Piece {

    Position move(Position currentPosition, Position destinationPosition);

    default Position capture(Position currentPosition, Position destinationPosition) {
        return move(currentPosition, destinationPosition);
    }

    boolean exist(Position checkingPosition);
}
