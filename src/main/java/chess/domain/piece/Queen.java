package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;
import java.util.List;

public class Queen extends Piece {

    public Queen(Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        int fileDiff = source.fileDiff(target);
        int rankDiff = source.rankDiff(target);

        return (Math.abs(fileDiff) == Math.abs(rankDiff))
                || (fileDiff == 0 || rankDiff == 0);
    }

    @Override
    public List<Position> findPath(Position source, Position target) {
        return findPathTemplate(source, target, this::calculateCount);
    }

    @Override
    protected int calculateCount(int rankDiff, int fileDiff) {
        if (Math.abs(fileDiff) == Math.abs(rankDiff)) {
            return Math.abs(fileDiff);
        }
        return Math.abs(fileDiff + rankDiff);
    }
}
