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
        final int distanceFile = destination.calculateDistanceFile(source);
        final int distanceRank = destination.calculateDistanceRank(source);
        if (!KnightVector.isExistMovableVector(distanceFile, distanceRank)) {
            throw new IllegalArgumentException("해당 기물이 움직일 수 있는 경로가 아닙니다.");
        }
        return new ArrayList<>(Arrays.asList(destination));
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
