package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;
import java.util.List;

public class King extends Piece {

    public King(Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        int fileDiff = source.fileDiff(target);
        int rankDiff = source.rankDiff(target);

        return Math.abs(fileDiff) <= 1 && Math.abs(rankDiff) <= 1;
    }

    @Override
    public List<Position> findPath(Position source, Position target) {
        return findPathTemplate(source, target, this::calculateCount);
    }

    @Override
    protected int calculateCount(int fileDiff, int rankDiff) {
        return Math.abs(fileDiff);
    }
}
