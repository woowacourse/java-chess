package chess.domain.piece;

import chess.domain.Position;
import java.util.List;

public class Knight extends Piece {

    @Override
    boolean isMovable(Position source, Position target) {
        int fileDiff = Math.abs(source.fileDiff(target));
        int rankDiff = Math.abs(source.rankDiff(target));

        return  Math.abs(fileDiff * rankDiff) == 2;
    }

    @Override
    List<Position> findPath(Position source, Position target) {
        return List.of(target);
    }
}
