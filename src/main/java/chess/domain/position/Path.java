package chess.domain.position;

import java.util.ArrayList;
import java.util.List;

public class Path {
    public static List<Position> of(Position start, Position end) {
        List<Position> path = new ArrayList<>();

        Position current = start;
        for (int i = 1; i < Distance.of(start, end).getDistance(); i++) {
            current = Direction.of(current, end).move(current);
            path.add(current);
        }

        return path;
    }

    public static List<Position> of(String start, String end) {
        return of(Position.of(start), Position.of(end));
    }
}
