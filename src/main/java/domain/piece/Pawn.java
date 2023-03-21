package domain.piece;

import domain.Location;
import domain.type.Color;
import domain.type.PieceType;
import java.util.function.BiPredicate;

public final class Pawn extends Piece {

    private Pawn(final Color color, BiPredicate<Location, Location> moveCheckStrategy) {
        super(color, PieceType.PAWN, moveCheckStrategy);
    }

    public static Pawn makeBlack() {
        return new Pawn(Color.BLACK, MoveCheckStrategy::isBlackPawnMove);
    }

    public static Pawn makeWhite() {
        return new Pawn(Color.WHITE, MoveCheckStrategy::isWhitePawnMove);
    }
}
