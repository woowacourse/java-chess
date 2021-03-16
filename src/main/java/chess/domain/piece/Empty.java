package chess.domain.piece;

public class Empty extends Piece {

    public static final char NAME = '.';

    public Empty(char horizontal, char vertical) {
        super(false, horizontal, vertical);
    }

    @Override
    void move(char horizontal, char vertical) {
        throw new UnsupportedOperationException("해당 메서드를 사용하면 안 됩니다.");
    }

    @Override
    char getName() {
        return NAME;
    }
}
