package chess.domain.piece;

public class Empty extends Piece {

    public static final char NAME = '.';

    public Empty(char x, char y) {
        super(false, x, y);
    }

    @Override
    public void move(char x, char y) {
        throw new UnsupportedOperationException("해당 메서드를 사용하면 안 됩니다.");
    }

    @Override
    void movable(char nextX, char nextY) {
        throw new UnsupportedOperationException("해당 메서드를 사용하면 안 됩니다.");
    }

    @Override
    char getName() {
        return NAME;
    }
}
