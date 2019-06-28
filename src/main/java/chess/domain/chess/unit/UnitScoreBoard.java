package chess.domain.chess.unit;

import chess.domain.chess.game.Team;
import chess.domain.geometric.Position;

import java.util.HashMap;
import java.util.Map;

public class UnitScoreBoard {
    private static final int SINGLE = 1;
    private static final double PAWN_RATIO_NORMAL_CASE = 1;
    private static final double PAWN_RATIO_WHEN_MULTIPLE_IN_SINGLE_ROW = 0.5;
    private static Map<String, Double> scores = new HashMap<>();

    private Map<Position, Unit> units;

    static {
        for (UnitInformation unit : UnitInformation.values()) {
            scores.put(unit.name(), unit.score());
        }
    }

    public UnitScoreBoard(Map<Position, Unit> units) {
        this.units = units;
    }

    public Map<Team, Double> sumScore() {
        Map<Team, Double> scoreInfo = new HashMap<>();

        for (Team team : Team.values()) {
            scoreInfo.put(team, sumScore(team));
        }

        return scoreInfo;
    }

    private Double sumScore(Team team) {
        double sum = 0;

        for (Position position : units.keySet()) {
            sum += singleUnitScore(team, position);
        }
        sum += sumPawnScore(team);

        return sum;
    }

    private double singleUnitScore(Team team, Position position) {
        if (units.get(position).getTeam() == team &&
                (!(units.get(position) instanceof Pawn))) {
            return scores.get(units.get(position).getName());
        }
        return 0;
    }

    public Double sumPawnScore(Team team) {
        double sum = 0;

        for (int y = Position.MIN_POSITION; y <= Position.MAX_POSITION; y++) {
            long numOfPawns = getNumberOfPawnsByColumn(y, team);
            sum += numOfPawns * ratio(numOfPawns);
        }

        return sum;
    }

    private long getNumberOfPawnsByColumn(int x, Team team) {
        return units.keySet().stream()
                .filter(key -> key.getX() == x)
                .filter(key -> units.get(key) instanceof Pawn)
                .filter(key -> units.get(key).getTeam() == team)
                .count();
    }

    private double ratio(long count) {
        if (count <= SINGLE) {
            return PAWN_RATIO_NORMAL_CASE;
        }
        return PAWN_RATIO_WHEN_MULTIPLE_IN_SINGLE_ROW;
    }
}
