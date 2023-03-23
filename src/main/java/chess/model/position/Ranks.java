package chess.model.position;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Ranks {

    private final Map<Rank, Position> columns;

    private Ranks(final Map<Rank, Position> columns) {
        this.columns = columns;
    }

    public static Ranks create() {
        return new Ranks(new EnumMap<>(Rank.class));
    }

    public void put(final Rank rank, final Position position) {
        this.columns.put(rank, position);
    }

    public Position getPosition(final Rank rank) {
        return this.columns.get(rank);
    }

    public List<Position> getPositionAll() {
        return new ArrayList<>(columns.values());
    }
}
