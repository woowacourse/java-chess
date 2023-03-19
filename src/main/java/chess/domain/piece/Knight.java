package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Knight extends Piece {
    private static final List<Integer> moveY = List.of(2, 1, -1, -2, -2, -1, 1, 2);
    private static final List<Integer> moveX = List.of(1, 2, 2, 1, -1, -2, -2, -1);

    private Knight(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static Knight from(final Color color) {
        return new Knight(PieceType.KNIGHT, color);
    }

    @Override
    public List<Position> findPositions(final Position source, final Position target) {
        int maxCases = 8;
        return IntStream.range(0, maxCases)
                .filter(canKnightMove(source, target))
                .mapToObj(i -> target)
                .collect(Collectors.toList());
    }

    private static IntPredicate canKnightMove(final Position source, final Position target) {
        return i -> target.getRow() == source.getRow() + moveX.get(i) && target.getColumn() == source.getColumn() + moveY.get(i);
    }
}
