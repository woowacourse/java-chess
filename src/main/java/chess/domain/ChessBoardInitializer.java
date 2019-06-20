package chess.domain;

import java.util.HashMap;
import java.util.Map;

public class ChessBoardInitializer implements Initializer {
    @Override
    public Map<Position, Unit> create() {
        Map<Position, Unit> units = new HashMap<>();
        createSingleTeam(units, Team.BLACK);
        createSingleTeam(units, Team.WHITE);

        return units;
    }

    private void createSingleTeam(Map<Position, Unit> map, Team team) {
        if (team.equals(Team.BLACK)) {
            initPawn(map, 1, team);
            initRook(map, 0, team);
            initKnights(map, 0, team);
            initBishop(map, 0, team);
            initKingQueen(map, 0, team);
        }
        initPawn(map, 6, team);
        initRook(map, 7, team);
        initKnights(map, 7, team);
        initBishop(map, 7, team);
        initKingQueen(map, 7, team);
    }

    private void initKingQueen(Map<Position, Unit> map, int row, Team team) {
        map.put(Position.create(3, row), new King(team));
        map.put(Position.create(4, row), new Queen(team));
    }

    private void initBishop(Map<Position, Unit> map, int row, Team team) {
        map.put(Position.create(2, row), new Bishop(team));
        map.put(Position.create(5, row), new Bishop(team));
    }

    private void initKnights(Map<Position, Unit> map, int row, Team team) {
        map.put(Position.create(1, row), new Knight(team));
        map.put(Position.create(6, row), new Knight(team));
    }

    private void initRook(Map<Position, Unit> map, int row, Team team) {
        map.put(Position.create(0, row), new Rook(team));
        map.put(Position.create(7, row), new Rook(team));
    }

    private void initPawn(Map<Position, Unit> map, int row, Team team) {
        for (int i = Position.MIN_POSITION; i < Position.MAX_POSITION; i++) {
            map.put(Position.create(i, row), new Pawn(team));
            map.put(Position.create(i, row), new Pawn(team));
        }
    }
}
