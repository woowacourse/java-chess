package view;

import chess.domain.chess.Team;
import chess.domain.chess.unit.Unit;

public class UnitMapper {
    public static String map(Unit unit) {
        if (unit.getTeam() == Team.BLACK)
            return unit.getClass().getSimpleName().substring(0, 1);
        return unit.getClass().getSimpleName().substring(0, 1).toLowerCase();
    }
}
