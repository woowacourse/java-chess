package chess.model.piece;

public class Empty extends Piece {
    private static final Piece INSTANCE = new Empty();

    private Empty() {
        super(Color.NONE, Type.NONE);
    }

    public static Piece getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValid(Movement movement) {
        throw new IllegalArgumentException("기물이 없는 경우에는 움직일 수 없음");
    }
}
