package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;
import java.util.List;

public class King extends Piece {

    public King(Team team) {
        super(team);
    }

    @Override
    boolean isMovable(Position source, Position target) {
        int fileDiff = source.fileDiff(target);
        int rankDiff = source.rankDiff(target);

        return Math.abs(fileDiff) <= 1 && Math.abs(rankDiff) <= 1;
    }

    @Override
    List<Position> findPath(Position source, Position target) {
        return List.of(target);
    }
}
