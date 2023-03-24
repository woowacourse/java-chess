package domain.piece;

import domain.position.Path;
import domain.position.Position;

public final class Knight extends Piece {

    public Knight(Color color) {
        super(PieceName.KNIGHT, color);
    }

    @Override
    public boolean isMovablePath(Position start, Path path) {
        Position end = path.getEndPosition();
        return isKnightMovablePosition(start.calculateRowGap(end), start.calculateColumnGap(end));
    }

    private static boolean isKnightMovablePosition(int rowGap, int columnGap) {
        return Math.abs(rowGap) * Math.abs(columnGap) == 2;
    }

    @Override
    protected boolean isMovableDirection(Position start, Position nextPosition) {
        throw new UnsupportedOperationException("[ERROR] Knight 객체에서는 지원하지 않는 기능입니다.");
    }

    @Override
    protected boolean isMovableDistance(int distance) {
        throw new UnsupportedOperationException("[ERROR] Knight 객체에서는 지원하지 않는 기능입니다.");
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
