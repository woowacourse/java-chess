package chess.domain.piece;

public class King extends Piece {

    private static final char NAME_WHEN_BLACK = 'K';
    private static final char NAME_WHEN_WHITE = 'k';

    public King(Boolean isBlack, char horizontal, char vertical) {
        super(isBlack, horizontal, vertical);
    }

    @Override
    public void move(char nextHorizontal, char nextVertical) {

    }

    private boolean isMovable() {
        return false;
    }

    @Override
    char getName() {
        if(isBlack()){
            return NAME_WHEN_BLACK;
        }
        return NAME_WHEN_WHITE;
    }
}
