package chess.domain.pieces;

import chess.domain.board.Position;

public abstract class Piece {

    private final Position position;

    public Piece(final Position position) {
        this.position = position;
    }

    public abstract void move(final String position);
}
