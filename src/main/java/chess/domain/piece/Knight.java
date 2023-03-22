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
    public List<Position> findMoveAblePositions(final Position source, final Position target, final Piece targetPiece) {
        final int lastIndexOfCase = 8;
        final int fastIndexOfCase = 0;

        List<Position> positions = IntStream.range(fastIndexOfCase, lastIndexOfCase)
                .filter(moveCase -> isMoveAble(source, target, moveCase))
                .mapToObj(moveCase -> target)
                .collect(Collectors.toList());

        validateInvalidColor(targetPiece);
        validateInvalidPosition(positions);
        return positions;
    }

    private boolean isMoveAble(final Position source, final Position target, final int moveCase) {
        try {
            int stayColumn = 0;
            int stayRow = 0;

            return target.isSameRow(source.move(moveX.get(moveCase), stayColumn))
                    && target.isSameColumn(source.move(stayRow, moveY.get(moveCase)));
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    private void validateInvalidPosition(List<Position> positions) {
        if (positions.isEmpty()) {
            throw new IllegalArgumentException("이동 할 수 없는 위치 입니다.");
        }
    }

}
