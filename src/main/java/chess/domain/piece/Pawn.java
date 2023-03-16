package chess.domain.piece;

import chess.domain.board.Square;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private final List<PawnVector> vectors;

    public Pawn(final Color color) {
        super(color);
        this.vectors = PawnVector.getVectorsByColor(color);
    }

    @Override
    public List<Square> findRoute(final Square source, final Square destination) {
        final int distanceX = destination.calculateDistanceX(source);
        final int distanceY = destination.calculateDistanceY(source);
        if (!isExistMovableVector(distanceX, distanceY)) {
            throw new IllegalArgumentException("해당 기물이 움직일 수 있는 경로가 아닙니다.");
        }
        return new ArrayList<>(List.of(destination));
    }

    private boolean isExistMovableVector(final int distanceX, final int distanceY) {
        return vectors.stream()
                .anyMatch(vector -> vector.isMovable(distanceX, distanceY));
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
