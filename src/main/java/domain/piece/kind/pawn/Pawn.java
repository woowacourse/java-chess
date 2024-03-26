package domain.piece.kind.pawn;

import domain.piece.Piece;
import domain.piece.Pieces;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Movement;
import domain.piece.attribute.point.Point;

import domain.piece.kind.PieceStatus;
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
    protected Set<Point> findLegalMovePoints(Pieces pieces){
        return getMovableDirection(pieces).stream()
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
    public PieceStatus getStatus() {
        return PieceStatus.PAWN;
    }


}
