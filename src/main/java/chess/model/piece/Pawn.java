package chess.model.piece;

import chess.model.Color;
import chess.model.position.Distance;
import java.util.List;

public class Pawn extends Piece {

    private static final List<Direction> white = Direction.whitePawn();
    private static final List<Direction> black = Direction.blackPawn();
    
    public Pawn(final Color color) {
        super(color, PieceType.PAWN);
    }

    @Override
    boolean movable(final Distance distance) {
        if (PieceColor.WHITE.equals(this.color)) {
            return isMovableByColor(white, distance);
        }

        return isMovableByColor(black, distance);
    }

    private boolean isMovableByColor(final List<Direction> target, final Distance distance) {
        return target.stream()
                .anyMatch(direction -> direction.match(distance.rank(), distance.file()));
    }
}
