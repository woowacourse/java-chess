package chess.domain.position;

import chess.domain.position.component.Column;
import chess.domain.position.component.Row;

import java.util.*;
import java.util.stream.Collectors;

public class Positions {
    private static final Map<String, Position> positions;

    static {
        positions = Arrays.stream(Column.values())
                .flatMap(column -> Arrays.stream(Row.values())
                        .map(row -> new Position(row, column)))
                .collect(Collectors.toMap(Position::key,
                        position -> position,
                        (a, b) -> a,
                        LinkedHashMap::new));
    }

    private Positions() {

    }

    public static Position of (String position) {
        Objects.requireNonNull(position);
        if(!positions.containsKey(position)) {
            throw new IllegalArgumentException("존재하지 않는 위치입니다.");
        }
        return positions.get(position);
    }

    public static Position of(Row row, Column column) {
        Objects.requireNonNull(row);
        Objects.requireNonNull(column);
        return Positions.of(Position.key(row, column));
    }

    public static List<Position> getValues() {
        return Collections.unmodifiableList(new ArrayList<>(positions.values()));
    }
}
