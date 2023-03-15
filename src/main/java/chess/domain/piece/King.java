package chess.domain.piece;

import chess.domain.Position;
import java.util.List;

public class King extends Piece {

    @Override
    boolean isMovable(Position source, Position target) {
        int xDiff = source.fileDiff(target);
        int yDiff = source.rankDiff(target);

        return Math.abs(xDiff) <= 1 && Math.abs(yDiff) <= 1;
    }

    @Override
    List<Position> findPath(Position source, Position target) {
        return List.of(target);
    }
}
