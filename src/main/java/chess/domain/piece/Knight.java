package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.List;
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
    public List<Position> findMoveAblePositions(final Position source, final Position target) {
        int maxCase = 8;

        List<Position> positions = IntStream.range(0, maxCase)
                .filter(moveCase -> canMove(source, target, moveCase))
                .mapToObj(moveCase -> target)
                .collect(Collectors.toList());

        if (positions.size() == 0) {
            throw new IllegalArgumentException("이동 할 수 없는 위치 입니다.");
        }
        return positions;
    }

    private boolean canMove(final Position source, final Position target, final int moveCase) {
        return target.isSameRow(source.getRow() + moveX.get(moveCase))
                && target.isSameColumn(source.getColumn() + moveY.get(moveCase));
    }
}
