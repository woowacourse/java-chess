package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(Team team) {
        super(team, Type.KNIGHT);
    }

    //TODO: King Knight 중복 제거
    @Override
    public List<Point> move(Point start, Point end) {
        List<Point> points = new ArrayList<>();
        Point vector = start.makeVector(end);
        if (type.getDirections().contains(Direction.findDirection(vector))) {
            points.add(end);
        }
        return points;
    }
}
