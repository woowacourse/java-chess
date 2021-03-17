package chess.domain.piece;

public class Rook extends Piece {
    private static final char NAME_WHEN_BLACK = 'R';
    private static final char NAME_WHEN_WHITE = 'r';

    public Rook(Boolean isBlack, char x, char y) {
        super(isBlack, x, y);
    }

    @Override
    void movable(char nextX, char nextY) {

    }

    @Override
    char getName() {
        if (isBlack()) {
            return NAME_WHEN_BLACK;
        }
        return NAME_WHEN_WHITE;
    }
}
