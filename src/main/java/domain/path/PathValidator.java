package domain.path;

import domain.piece.Piece;
import domain.type.PieceType;
import domain.type.direction.PieceMoveDirection;
import java.util.List;

public final class PathValidator {

    private static final List<PieceMoveDirection> MOVE_DIRECTIONS = List.of(
        PieceMoveDirection.UP,
        PieceMoveDirection.DOWN
    );
    private static final List<PieceMoveDirection> ATTACK_DIRECTIONS = List.of(
        PieceMoveDirection.RIGHT_UP,
        PieceMoveDirection.LEFT_UP,
        PieceMoveDirection.RIGHT_DOWN,
        PieceMoveDirection.LEFT_DOWN
    );

    public boolean isValid(final PieceMoveDirection direction, final Piece startPiece, final Path path) {
        if (path.isBlocked()) {
            return false;
        }
        Piece endPiece = path.getEnd().getPiece();
        if (startPiece.isSameType(PieceType.PAWN)) {
            return isValidPawnPath(direction, startPiece, endPiece);
        }
        return startPiece.isDifferentColor(endPiece);
    }

    private boolean isValidPawnPath(PieceMoveDirection direction, Piece startPiece, Piece endPiece) {
        if (MOVE_DIRECTIONS.contains(direction) && endPiece.isSameType(PieceType.EMPTY)) {
            return true;
        }
        return ATTACK_DIRECTIONS.contains(direction) && startPiece.isEnemy(endPiece);
    }
}
