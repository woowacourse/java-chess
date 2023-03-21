package chess.domain.piece;

public class Empty extends Piece {
    private static final Empty empty = new Empty();

    private Empty() {
        super(Camp.EMPTY, Role.EMPTY);
    }

    public static Empty of() {
        return empty;
    }
}
