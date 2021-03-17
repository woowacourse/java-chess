package chess.domain.piece;

public class King extends Piece {
    private static final char NAME_WHEN_BLACK = 'K';
    private static final char NAME_WHEN_WHITE = 'k';
    private static final int VALID_KING_MOVEMENT = 1;

    public King(Boolean isBlack, char x, char y) {
        super(isBlack, x, y);
    }

    @Override
    void movable(char nextX, char nextY) {
        Position currentPosition = getPosition();
        Position nextPosition = new Position(nextX, nextY);
        PositionDistance difference = currentPosition.calculateDistance(nextPosition);
        if (Math.abs(difference.getXDistance()) > VALID_KING_MOVEMENT || Math.abs(difference.getYDistance()) > VALID_KING_MOVEMENT) {
            throw new IllegalArgumentException("이동할 수 있는 범위를 벗어났습니다.");
        }
    }

    @Override
    char getName() {
        if (isBlack()) {
            return NAME_WHEN_BLACK;
        }
        return NAME_WHEN_WHITE;
    }
}
