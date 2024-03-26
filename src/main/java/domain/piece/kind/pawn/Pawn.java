package domain.piece.kind.pawn;

import domain.piece.Piece;
import domain.piece.Pieces;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Movement;
import domain.piece.attribute.point.Point;

import domain.piece.kind.PieceStatus;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Pawn extends Piece {
    protected boolean moved;

    protected Pawn(Point point, Color color) {
        super(point, color);
        this.moved = false;
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
        return pieces.findPieceWithPoint(point.move(movement))
                .filter(this::isOpposite)
                .isPresent();
    }

    @Override
    protected Piece update(Point point) {
        moved = true;
        return null;
    }

    public boolean canMove(final Point point) {
        return false;
    }


    @Override
    public PieceStatus getStatus() {
        return PieceStatus.PAWN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Pawn pawn = (Pawn) o;
        return moved == pawn.moved;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), moved);
    }
}
