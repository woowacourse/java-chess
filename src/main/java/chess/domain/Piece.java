package chess.domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Piece {

    private final boolean isWhite;
    private final boolean isFinite;
    private final Set<Movement> movements;

    public Piece(boolean isWhite, boolean isFinite, List<Movement> movements) {
        this.isWhite = isWhite;
        this.isFinite = isFinite;
        this.movements = movements.stream()
                .flatMap(this::flipMovement)
                .collect(Collectors.toSet());
    }

    private Stream<Movement> flipMovement(Movement movement) {
        return Stream.of(
                movement,
                movement.flipHorizontal(),
                movement.flipVertical(),
                movement.flipHorizontal().flipVertical()
        );
    }

    public boolean hasSameColor(Piece otherPiece) {
        return isWhite == otherPiece.isWhite;
    }

    public boolean hasMovement(Movement movement) {
        boolean hasMovement = false;
        for (Movement pieceMovement : movements) {
            hasMovement = hasMovement || compareMovement(pieceMovement, movement);
        }
        return hasMovement;
    }

    private boolean compareMovement(Movement pieceMovement, Movement movement) {
        if (isFinite) {
            return pieceMovement.equals(movement);
        }
        return pieceMovement.equals(movement.getUnitMovement());
    }
}
