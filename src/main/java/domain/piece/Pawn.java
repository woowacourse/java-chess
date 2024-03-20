package domain.piece;

import domain.piece.point.Point;

import java.util.List;

public class Pawn extends Piece {
    private static List<Direction> blackList = List.of(Direction.DOWN, Direction.DOWN_LEFT, Direction.DOWN_RIGHT);
    private static List<Direction> whiteList = List.of(Direction.UP, Direction.UP_LEFT, Direction.UP_RIGHT);

    public Pawn(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.PAWN;
    }

    public boolean canMove(Point point) {
        if (isEqualColor(Color.BLACK)) {
            return blackList.stream()
                            .map(direction -> direction.move(this.getPoint()))
                            .anyMatch(point::equals);
        }
        if (isEqualColor(Color.WHITE)) {
            return whiteList.stream()
                            .map(direction -> direction.move(this.getPoint()))
                            .anyMatch(point::equals);
        }
        return false;
    }

}
