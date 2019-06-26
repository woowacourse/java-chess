package chess.domain.chess.initializer;

import chess.domain.chess.Team;
import chess.domain.chess.unit.*;
import chess.domain.geometric.Position;

import java.util.HashMap;
import java.util.Map;

public class ChessBoardInitializer implements Initializer {
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;

    @Override
    public Map<Position, Unit> create() {
        Map<Position, Unit> units = new HashMap<>();
        createSingleTeam(units, Team.BLACK);
        createSingleTeam(units, Team.WHITE);

        return units;
    }

    @Override
    public Team createTeam() {
        return Team.WHITE;
    }

    private void createSingleTeam(Map<Position, Unit> map, Team team) {
        if (team.equals(Team.WHITE)) {
            initPawn(map, ONE, team);
            initOther(map, ZERO, team);
            return;
        }
        initPawn(map, SIX, team);
        initOther(map, SEVEN, team);
    }

    private void initOther(Map<Position, Unit> map, int row, Team team) {
        initRook(map, row, team);
        initKnights(map, row, team);
        initBishop(map, row, team);
        initKingQueen(map, row, team);
    }

    private void initKingQueen(Map<Position, Unit> map, int row, Team team) {
        map.put(Position.create(THREE, row), new King(team));
        map.put(Position.create(FOUR, row), new Queen(team));
    }

    private void initBishop(Map<Position, Unit> map, int row, Team team) {
        map.put(Position.create(TWO, row), new Bishop(team));
        map.put(Position.create(FIVE, row), new Bishop(team));
    }

    private void initKnights(Map<Position, Unit> map, int row, Team team) {
        map.put(Position.create(ONE, row), new Knight(team));
        map.put(Position.create(SIX, row), new Knight(team));
    }

    private void initRook(Map<Position, Unit> map, int row, Team team) {
        map.put(Position.create(ZERO, row), new Rook(team));
        map.put(Position.create(SEVEN, row), new Rook(team));
    }

    private void initPawn(Map<Position, Unit> map, int row, Team team) {
        for (int i = Position.MIN_POSITION; i < Position.MAX_POSITION; i++) {
            map.put(Position.create(i, row), new Pawn(team));
            map.put(Position.create(i, row), new Pawn(team));
        }
    }
}
