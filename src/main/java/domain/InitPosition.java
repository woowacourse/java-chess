package domain;

import java.util.List;
import java.util.function.Function;

public enum InitPosition {

    ROOK(List.of(Horizontal.A, Horizontal.H), majorPieceVertical()),
    KNIGHT(List.of(Horizontal.B, Horizontal.G), majorPieceVertical()),
    BISHOP(List.of(Horizontal.C, Horizontal.F), majorPieceVertical()),
    QUEEN(List.of(Horizontal.D), majorPieceVertical()),
    KING(List.of(Horizontal.E), majorPieceVertical()),
    PAWN(List.of(Horizontal.values()), pawnVertical()),
    ;

    private final List<Horizontal> horizontals;
    private final Function<Side, Vertical> vertical;

    InitPosition(List<Horizontal> horizontals, Function<Side, Vertical> vertical) {
        this.horizontals = horizontals;
        this.vertical = vertical;
    }

    private static Function<Side, Vertical> majorPieceVertical() {
        return side -> {
            if (side.isBlack()) {
                return Vertical.EIGHTH;
            }
            return Vertical.FIRST;
        };
    }

    private static Function<Side, Vertical> pawnVertical() {
        return side -> {
            if (side.isBlack()) {
                return Vertical.SEVENTH;
            }
            return Vertical.SECOND;
        };
    }

    public Vertical vertical(Side side) {
        return vertical.apply(side);
    }

    public List<Horizontal> getHorizontals() {
        return horizontals;
    }

    public Function<Side, Vertical> getVertical() {
        return vertical;
    }
}
