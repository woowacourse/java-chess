package chess.domain.piece;

public class Empty extends Piece {
    private static final Empty INSTANCE = new Empty();

    private Empty() {
        super(null, null);
    }

    public static Empty getInstance() {
        return INSTANCE;
    }

    @Override
    protected int maxPassMoveCount() {
        throw new IllegalArgumentException("시작 위치가 비어있습니다.");
    }

    @Override
    protected int maxAttackMoveCount() {
        throw new IllegalArgumentException("시작 위치가 비어있습니다.");
    }

    @Override
    public void move() {
        throw new IllegalArgumentException("시작 위치가 비어있습니다.");
    }
}
