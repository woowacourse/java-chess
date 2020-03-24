package chess.domain.piece;

import chess.domain.board.Position;

public class Piece {
    private Type type;
    private Side side;

    public Piece(final Type type, final Side side) {
        this.type = type;
        this.side = side;
    }

    public void move(Position end) {

    }

    public String name() {
        return type.getName();
    }
}
