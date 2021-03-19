package chess.domain.piece;

import chess.domain.Color;

public class Empty extends Piece {
    public static final Empty EMPTY = new Empty(Position.EMPTY, ".", Color.NONE);

    public Empty(Position position, String name, Color color) {
        super(position, name, color);
    }

    @Override
    void move(Position target, CurrentPieces currentPieces) {

    }
}
