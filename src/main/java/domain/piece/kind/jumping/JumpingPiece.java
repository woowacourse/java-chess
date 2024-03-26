package domain.piece.kind.jumping;

import domain.piece.Piece;
import domain.piece.Pieces;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Point;
import domain.piece.attribute.point.Movement;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class JumpingPiece extends Piece {

    protected JumpingPiece(Point point, Color color) {
        super(point, color);
    }
    @Override
    protected final Set<Point> findLegalMovePoints(Pieces pieces) {
        return getMovableDirection().stream()
                .filter(direction -> super.point.canMove(direction ))
                .map(direction -> super.point.move(direction))
                .filter(point -> !pieces.isFriend(this, point))
                .collect(Collectors.toSet());
    }


    protected abstract Set<Movement> getMovableDirection();
}
