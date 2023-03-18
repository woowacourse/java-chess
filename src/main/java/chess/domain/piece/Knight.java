package chess.domain.piece;

import chess.domain.board.Square;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(final Color color) {
        super(color);
    }

    @Override
    public List<Square> findRoute(final Square source, final Square destination) {
        final int distanceX = destination.calculateDistanceX(source);
        final int distanceY = destination.calculateDistanceY(source);
        if (!KnightVector.isExistMovableVector(distanceX, distanceY)) {
            throw new IllegalArgumentException("해당 기물이 움직일 수 있는 경로가 아닙니다.");
        }
        return new ArrayList<>(List.of(destination));
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
