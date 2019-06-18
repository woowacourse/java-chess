package chess.domain;

import java.util.Map;
import java.util.Optional;

public class ChessBoard {
    private Map<Position, Unit> units;

    public ChessBoard(Initializer initializer) {
        this.units = initializer.create();
    }

    public Optional<Unit> getUnit(Position position) {
        return Optional.ofNullable(units.get(position));
    }

    public static String map(Unit unit) {
        if (unit.getTeam() == Team.BLACK) {
            return unit.getClass().getSimpleName().substring(0, 1);
        }
        return unit.getClass().getSimpleName().substring(0, 1).toLowerCase();
    }
}
