package chess.domain.piece;

public class Empty extends Piece {
    public static final Empty EMPTY = new Empty(Position.of('0', '0'), ".");

    public Empty(Position position, String name) {
        super(position, name);
    }
}
