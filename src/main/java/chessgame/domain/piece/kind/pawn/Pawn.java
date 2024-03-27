package chessgame.domain.piece.kind.pawn;

import chessgame.domain.piece.attribute.point.Movement;
import chessgame.domain.piece.attribute.point.Point;
import chessgame.domain.piece.Piece;
import chessgame.domain.piece.Pieces;
import chessgame.domain.piece.attribute.Color;

import chessgame.domain.piece.kind.PieceStatus;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Pawn extends Piece {
    protected Pawn(Point point, Color color) {
        super(point, color);
    }

    public static Pawn from(Point point, Color color) {
        if (color.isBlack()) {
            return new BlackPawn(point, color);
        }
        return new WhitePawn(point, color);
    }

    @Override
    protected Set<Point> findLegalMovePoints(Pieces pieces) {
        return getMovableDirection(pieces).stream()
                .filter(direction -> super.point.canMove(direction))
                .map(direction -> super.point.move(direction))
                .filter(point -> !pieces.isFriend(this, point))
                .collect(Collectors.toSet());

    }

    protected abstract Set<Movement> getMovableDirection(Pieces pieces);

    protected final boolean hasEnemy(Pieces pieces, Movement movement) {
        if (!point.canMove(movement)) {
            return false;
        }
        return pieces.findPieceWithPoint(point.move(movement))
                .filter(this::isOpposite)
                .isPresent();
    }

    @Override
    public PieceStatus status() {
        return PieceStatus.PAWN;
    }


}
