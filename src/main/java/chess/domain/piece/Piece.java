package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public abstract class Piece {

    protected Team team;

    public Piece(Team team) {
        this.team = team;
    }

    public abstract boolean isMovable(Position source, Position target);
    public abstract List<Position> findPath(Position source, Position target);
    protected abstract int calculateCount(int fileDiff, int rankDiff);

    public boolean isSameTeam(Piece otherPiece) {
        return team.isSameTeam(otherPiece.team);
    }

    public boolean isSameTeam(Team team) {
        return this.team.isSameTeam(team);
    }

    public boolean isPawn() {
        return false;
    }

    public Team getTeam() {
        return team;
    }

    public List<Position> findPathTemplate(Position source, Position target, BiFunction<Integer, Integer, Integer> calculateCount) {
        int rankDiff = source.rankDiff(target);
        int fileDiff = source.fileDiff(target);

        int count = calculateCount.apply(rankDiff, fileDiff);
        int rankUnit = rankDiff / count;
        int fileUnit = fileDiff / count;

        return makePath(count, rankUnit, fileUnit, source);
    }

    private List<Position> makePath(int count, int rankUnit, int fileUnit, Position current) {
        List<Position> path = new ArrayList<>();

        for(int i = 0; i < count; i++) {
            current = current.moveBy(rankUnit, fileUnit);
            path.add(current);
        }

        return path;
    }
}
