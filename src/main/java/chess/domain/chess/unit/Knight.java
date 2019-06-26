package chess.domain.chess.unit;

import chess.domain.UnitInformation;
import chess.domain.chess.Team;
import chess.domain.geometric.Vector;
import chess.util.DoubleCompare;

public class Knight extends Unit {
    private static final Double KNIGHT_LENGTH = Math.sqrt(5);

    public Knight(Team team) {
        super(team, UnitInformation.KNIGHT.name());
    }

    @Override
    public boolean validateDirection(Vector vector) {
        return DoubleCompare.deltaCompare(vector.calculateDistance(), KNIGHT_LENGTH);
    }


    @Override
    public String toString() {
        return getTeam().applyUpperCaseOrLowerCaseByTeam(UnitInformation.KNIGHT.symbol());
    }
}
