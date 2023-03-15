package chess.domain.piece;

import chess.domain.board.Square;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knight extends Piece {

    public Knight(final Color color) {
        super(color);
    }

    @Override
    public List<Square> findRoute(final Square source, final Square destination) {
        final int distanceX = destination.calculateDistanceX(source);
        final int distanceY = destination.calculateDistanceY(source);
        if (KnightVector.isExistMovableVector(distanceX, distanceY)) {
            return new ArrayList<>(Arrays.asList(destination));
        }
        throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
    }
}
