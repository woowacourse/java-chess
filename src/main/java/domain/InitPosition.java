package domain;

import java.util.List;
import java.util.function.Function;

public enum InitPosition {

    ROOK(List.of(Rank.A, Rank.H), majorPieceVertical()),
    KNIGHT(List.of(Rank.B, Rank.G), majorPieceVertical()),
    BISHOP(List.of(Rank.C, Rank.F), majorPieceVertical()),
    QUEEN(List.of(Rank.D), majorPieceVertical()),
    KING(List.of(Rank.E), majorPieceVertical()),
    PAWN(List.of(Rank.values()), pawnVertical()),
    ;

    private final List<Rank> ranks;
    private final Function<Side, File> vertical;

    InitPosition(List<Rank> ranks, Function<Side, File> vertical) {
        this.ranks = ranks;
        this.vertical = vertical;
    }

    private static Function<Side, File> majorPieceVertical() {
        return side -> {
            if (side.isBlack()) {
                return File.EIGHTH;
            }
            return File.FIRST;
        };
    }

    private static Function<Side, File> pawnVertical() {
        return side -> {
            if (side.isBlack()) {
                return File.SEVENTH;
            }
            return File.SECOND;
        };
    }

    public File vertical(Side side) {
        return vertical.apply(side);
    }

    public List<Rank> getHorizontals() {
        return ranks;
    }
}
