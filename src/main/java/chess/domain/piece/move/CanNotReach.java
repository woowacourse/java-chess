package chess.domain.piece.move;

abstract class CanNotReach implements CanNotMoveStrategy {
    protected final int maxDistance;
    CanNotReach(int maxDistance) {
        this.maxDistance = maxDistance;
    }
}

