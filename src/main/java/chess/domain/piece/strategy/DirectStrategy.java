package chess.domain.piece.strategy;

import chess.domain.board.Square;
import chess.domain.piece.strategy.vector.DirectVector;
import java.util.ArrayList;
import java.util.List;

public class DirectStrategy implements Strategy {

    private final List<DirectVector> vectors;

    public DirectStrategy(final List<DirectVector> vectors) {
        this.vectors = vectors;
    }

    @Override
    public List<Square> findRoute(final Square source, final Square destination) {
        final int distanceFile = destination.calculateDistanceFile(source);
        final int distanceRank = destination.calculateDistanceRank(source);
        if (!isExistMovableVector(distanceFile, distanceRank)) {
            throw new IllegalArgumentException("해당 기물이 움직일 수 있는 경로가 아닙니다.");
        }
        return new ArrayList<>(List.of(destination));
    }

    private boolean isExistMovableVector(final int distanceFile, final int distanceRank) {
        return vectors.stream()
                .anyMatch(vector -> vector.isMovable(distanceFile, distanceRank));
    }
}
