package chess.domain.piece;

public class Empty extends Piece {
    public static final Empty EMPTY = new Empty(Position.EMPTY, ".");

    public Empty(Position position, String name) {
        super(position, name);
    }
}
