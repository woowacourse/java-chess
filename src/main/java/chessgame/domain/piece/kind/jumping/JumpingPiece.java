package chessgame.domain.piece.kind.jumping;

import chessgame.domain.piece.attribute.Color;
import chessgame.domain.piece.attribute.point.Movement;
import chessgame.domain.piece.attribute.point.Point;
import chessgame.domain.piece.Piece;
import chessgame.domain.piece.Pieces;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class JumpingPiece extends Piece {

    protected JumpingPiece(Point point, Color color) {
        super(point, color);
    }

    @Override
    protected final Set<Point> findLegalMovePoints(final Pieces pieces) {
        return getMovableDirection().stream()
                .filter(direction -> super.point.canMove(direction))
                .map(direction -> super.point.move(direction))
                .filter(point -> !pieces.isFriend(this, point))
                .collect(Collectors.toSet());
    }


    protected abstract Set<Movement> getMovableDirection();
}
