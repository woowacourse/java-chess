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
        if (isExistMovableVector(distanceX, distanceY)) {
            return new ArrayList<>(List.of(destination));
        }
        throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
    }

    private boolean isExistMovableVector(final int distanceX, final int distanceY) {
        return vectors.stream()
                .anyMatch(vector -> vector.isMovable(distanceX, distanceY));
    }
}
