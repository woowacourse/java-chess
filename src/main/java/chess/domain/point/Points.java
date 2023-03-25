package chess.domain.point;

import chess.cache.PieceCache;
import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class Points {
    private static final int INCREASE_COUNT = 1;
    private static final int DECREASE_COUNT = -1;
    private static final Point PAWN_PENALTY_POINT = Point.create(0.5);

    private final Map<Piece, Integer> pointCounts;

    private Points(final Map<Piece, Integer> pointCounts) {
        this.pointCounts = pointCounts;
    }

    public static Points create() {
        final Map<Piece, Integer> point = new HashMap<>();
        PieceCache.create()
                .values()
                .forEach(piece -> point.put(piece, point.getOrDefault(piece, 0) + INCREASE_COUNT));
        return new Points(point);
    }

    public void decrease(final Piece piece) {
        pointCounts.put(piece, pointCounts.getOrDefault(piece, 0) + DECREASE_COUNT);
    }

    public Point calculatePoint(final Color color, final int pawnDuplicatedColumnCount) {
        final List<PieceType> pieceTypes = pointCounts.keySet()
                .stream()
                .filter(key -> key.isSameColor(color))
                .flatMap(this::createPieceTypes)
                .collect(Collectors.toList());

        final Point totalPoint = PieceType.sum(pieceTypes);
        final Point penaltyPoint = PAWN_PENALTY_POINT.times(pawnDuplicatedColumnCount);
        return totalPoint.minus(penaltyPoint);
    }

    private Stream<PieceType> createPieceTypes(final Piece piece) {
        return IntStream.range(0, pointCounts.get(piece)).mapToObj(count -> piece.getPieceType());
    }
}
