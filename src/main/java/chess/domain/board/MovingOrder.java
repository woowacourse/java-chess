package chess.domain.board;

import chess.domain.piece.Color;

public class MovingOrder {
    private final Position from;
    private final Position to;

    public MovingOrder(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    public boolean isSameDirection(Direction direction) {
        return from.getXDistance(to) == direction.getX() &&
                from.getYDistance(to) == direction.getY();
    }

    public boolean isInitLineOfPawn(Color color) {
        return (color == Color.BLACK && from.isEqualRank(Rank.SEVEN) ||
                (color == Color.WHITE && from.isEqualRank(Rank.TWO)));
    }

    public boolean isPawnInitDirectionOf(Direction direction) {
        return from.getXDistance(to) == direction.getX()
                && direction.getY() * from.getYDistance(to) <= 2;
    }
}
