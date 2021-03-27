package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Path {

    private final List<Position> positions;

    private Path(final List<Position> positions) {
        this.positions = positions;
    }

    public boolean contains(final Position targetPosition) {
        return positions.contains(targetPosition);
    }

    public List<Position> removeObstacleInPath(Piece piece, Board boardPosition) {
        final List<Position> cleanPath = new ArrayList<>();
        for (Position position : positions) {
            // TODO
//            if (piece.canGo(position)) {
//                cleanPath.add(position);
//            }
            // 같은 색일 때 (공용)
                // 그만
            // 다른 색일 때
                // pawn diagonal then OK
            // 빈 공간일 때
                // pawn straight then OK
        }

        return cleanPath;
    }

    public boolean isEmpty() {
        return positions.isEmpty();
    }
}
