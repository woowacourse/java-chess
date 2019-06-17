package chess.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChessBoardInitializer implements Initializer {

    @Override
    public List<Unit> create() {
        List<Unit> units = new ArrayList<>();
        units.addAll(createSingleTeam(Team.BLACK));
        units.addAll(createSingleTeam(Team.WHITE));


        return units;
    }

    private List<Unit> createSingleTeam(Team team) {
        List<Unit> units = new ArrayList<>();

        if (team.equals(Team.BLACK)) {
            units.addAll(initPawn(1, Team.BLACK));
            units.addAll(initRook(0,team));
            units.addAll(initKnights(0,team));
            units.addAll(initBishop(0,team));
            units.addAll(initKingQueen(0,team));
            return units;
        }

        units.addAll(initPawn(6, Team.WHITE));
        units.addAll(initRook(7,team));
        units.addAll(initKnights(7,team));
        units.addAll(initBishop(7,team));
        units.addAll(initKingQueen(7, team));
        return units;
    }

    private List<Unit> initKingQueen(int row, Team team) {
        List<Unit> units = new ArrayList<>();

        units.add(new King(new Position(3, row), team));
        units.add(new Queen(new Position(4, row), team));

        return units;
    }

    private List<Unit> initBishop(int row, Team team) {
        List<Unit> units = new ArrayList<>();

        units.add(new Bishop(new Position(2, row), team));
        units.add(new Bishop(new Position(5, row), team));

        return units;
    }

    private List<Unit> initKnights(int row, Team team) {
        List<Unit> units = new ArrayList<>();

        units.add(new Rook(new Position(1, row), team));
        units.add(new Rook(new Position(6, row), team));

        return units;
    }

    private List<Unit> initRook(int row, Team team) {
        List<Unit> units = new ArrayList<>();

        units.add(new Rook(new Position(0, row), team));
        units.add(new Rook(new Position(7, row), team));

        return units;
    }

    private List<Unit> initPawn(int row, Team team) {
        List<Unit> pawns = new ArrayList<>();
        for (int i = Position.MIN_POSITION; i < Position.MAX_POSITION; i++) {
            pawns.add(new Pawn(new Position(i, row), team));

        }
        return pawns;
    }
}
