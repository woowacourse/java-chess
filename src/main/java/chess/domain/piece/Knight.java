package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.Collections;
import java.util.List;

public class Knight extends Piece {
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
        for (int index = 0; index < 8; index++) {
            int expectRow = source.getRow() + moveX.get(index);
            int expectColumn = source.getColumn() + moveY.get(index);

            if (target.getRow() == expectRow && target.getColumn() == expectColumn) {
                return List.of(target);
            }
        }
        return Collections.emptyList();
    }
}
