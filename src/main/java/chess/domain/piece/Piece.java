package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;

public abstract class Piece {
    private static final char MIN_COLUMN_RANGE = 'a';
    private static final char MAX_COLUMN_RANGE = 'h';
    private static final char MIN_ROW_RANGE = '1';
    private static final char MAX_ROW_RANGE = '8';
    private Position position;
    private final boolean isBlack;

    public Piece(final boolean isBlack, final char x, final char y) {
        this.position = new Position(x, y);
        this.isBlack = isBlack;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public Position getPosition() {
        return position;
    }

    public void move(final char nextX, final char nextY) {
        movable(nextX, nextY);
        this.position = new Position(nextX, nextY);
    }

    public boolean isOutOfRange(final Position position) {
        char x = position.getX();
        char y = position.getY();
        return (x < MIN_COLUMN_RANGE || x > MAX_COLUMN_RANGE || y < MIN_ROW_RANGE || y > MAX_ROW_RANGE);
    }

    private void movable(final char nextX, final char nextY) {
        List<Position> movablePositions = extractMovablePositions();
        if (!movablePositions.contains(new Position(nextX, nextY))) {
            throw new IllegalArgumentException("이동할 수 있는 범위를 벗어났습니다.");
        }
    }

    abstract List<Position> extractMovablePositions();

    abstract char getName();
}