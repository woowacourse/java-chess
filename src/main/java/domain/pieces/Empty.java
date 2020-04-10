package domain.pieces;

import domain.move.Direction;
import domain.pieces.exceptions.IsNotMovableException;
import domain.point.MovePoint;
import domain.point.Point;
import domain.team.Team;
import java.util.List;
import java.util.Map;

public class Empty extends Piece {

    private static final String INITIAL = ".";

    private static final double score = 0;

    public Empty(Team team) {
        super(INITIAL, team);
    }

    @Override
    public boolean isMovable(Direction direction, Map<Point, Piece> pieces, MovePoint movePoint) {
        return false;
    }

    @Override
    public List<Direction> getDirection(Team team) {
        throw new IsNotMovableException("움직일 수 없는 말입니다.");
    }

    @Override
    public boolean isNoneTeam() {
        return true;
    }

    @Override
    public double getScore(Map<Point, Piece> pieces, Point point) {
        return score;
    }
}
