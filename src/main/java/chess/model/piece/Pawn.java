package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import chess.model.piece.strategy.PawnMovableStrategy;
import java.util.List;

public final class Pawn extends Piece {

    public Pawn(Color color, Square square) {
        super(color, square, new PawnMovableStrategy(nonAttackDirection(color), attackDirection(color)));
    }

    @Override
    public Point getPoint() {
        return Point.PAWN;
    }

    public boolean isFirstLocation() {
        return square().isPawnFirstSquare(color());
    }

    @Override
    public String getLetter() {
        return "p";
    }

    private static List<Direction> nonAttackDirection(Color color) {
        if (color.isBlack()) {
            return List.of(Direction.SOUTH);
        }
        return List.of(Direction.NORTH);
    }

    private static List<Direction> attackDirection(Color color) {
        if (color.isBlack()) {
            return List.of(Direction.SOUTHEAST, Direction.SOUTHWEST);
        }
        return List.of(Direction.NORTHEAST, Direction.NORTHWEST);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
