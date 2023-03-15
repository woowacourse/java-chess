package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;
import java.util.List;

public class Knight extends Piece {

    public Knight(Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        int fileDiff = Math.abs(source.fileDiff(target));
        int rankDiff = Math.abs(source.rankDiff(target));

        return  Math.abs(fileDiff * rankDiff) == 2;
    }

    @Override
    public List<Position> findPath(Position source, Position target) {
        return List.of(target);
    }
}
