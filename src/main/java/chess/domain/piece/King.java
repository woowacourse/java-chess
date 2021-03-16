package chess.domain.piece;

import java.util.Arrays;

public class King extends Piece {
    public enum Move {
        RIGHT(1, 0),
        LEFT(-1, 0),
        UP(0, 1),
        DOWN(0, -1),
        LEFT_UP(-1, 1),
        RIGHT_UP(1, 1),
        LEFT_DOWN(-1, -1),
        RIGHT_DOWN(1, -1);

        private int x;
        private int y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        Move(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static final char NAME_WHEN_BLACK = 'K';
    private static final char NAME_WHEN_WHITE = 'k';

    public King(Boolean isBlack, char x, char y) {
        super(isBlack, x, y);
    }

    @Override
    void movable(char nextX, char nextY) {
        Position currentPosition = getPosition();
        Position nextPosition = new Position(nextX, nextY);

        Arrays.stream(Move.values())
                .filter((travel) -> {
                    return currentPosition.next(travel.getX(), travel.getY()).equals(nextPosition);
                })
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("이동할 수 있는 범위를 벗어났습니다."));
    }

    @Override
    char getName() {
        if (isBlack()) {
            return NAME_WHEN_BLACK;
        }
        return NAME_WHEN_WHITE;
    }
}
