package chess.domain.piece.direction;

import chess.domain.position.Position;

public abstract class Direction implements MoveStrategy {
    private int x;
    private int y;

    protected Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Position move(final Position position) {
        return position.change(y, x);
    }

    @Override
    public final int getX() {
        return x;
    }

    @Override
    public final int getY() {
        return y;
    }
}
