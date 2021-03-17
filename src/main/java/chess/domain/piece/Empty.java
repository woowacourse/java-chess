package chess.domain.piece;

public class Empty extends Piece {
    public static final Empty EMPTY = new Empty(Position.of(-1, -1), ".");

    public Empty(Position position, String name) {
        super(position, name);
    }
}
