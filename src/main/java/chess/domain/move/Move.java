package chess.domain.move;

import chess.domain.piece.Position;

public abstract class Move {

    Position move(final Position before, final Direction direction) {
        return direction.calculate(before);
    }
}

