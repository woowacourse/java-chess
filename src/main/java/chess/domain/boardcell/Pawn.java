package chess.domain.boardcell;

import chess.domain.*;

import java.util.*;

public class Pawn extends ChessPiece {
    private static Map<Team, Pawn> pawns = new HashMap<>();

    static Pawn getInstance(Team team) {
        if (!pawns.containsKey(team)) {
            pawns.put(team, new Pawn(team));
        }
        return pawns.get(team);
    }

    private Pawn(Team team) {
        super(team);
    }

    @Override
    PieceType getPieceTypeByTeam(Team team) {
        if (team == Team.BLACK) {
            return PieceType.PAWN_BLACK;
        }
        if (team == Team.WHITE) {
            return PieceType.PAWN_WHITE;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Set<CoordinatePair> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, CoordinatePair from) {
        if (from.matchY(CoordinateY.RANK_1) || from.matchY(CoordinateY.RANK_8)) {
            return Collections.emptySet();
        }
        if (getType().getTeam() == Team.WHITE) {
            return handleWhenWhite(pieceTeamProvider, from);
        }

        return handleWhenBlack(pieceTeamProvider, from);
    }

    private Set<CoordinatePair> handleWhenWhite(PieceTeamProvider pieceTeamProvider, CoordinatePair from) {
        Set<CoordinatePair> movableCoordinates = new HashSet<>();
        from.move(Direction.UP)
            .filter(coord -> pieceTeamProvider.getTeamAt(coord).isEmpty())
            .ifPresent(movableCoordinates::add);
        from.move(Direction.LEFT_TOP)
            .filter(coord -> pieceTeamProvider.getTeamAt(coord).isEnemy(getType().getTeam()))
            .ifPresent(movableCoordinates::add);
        from.move(Direction.RIGHT_TOP)
            .filter(coord -> pieceTeamProvider.getTeamAt(coord).isEnemy(getType().getTeam()))
            .ifPresent(movableCoordinates::add);

        checkIfWhiteFirstMove(pieceTeamProvider, from)
            .ifPresent(movableCoordinates::add);

        return movableCoordinates;
    }

    private Optional<CoordinatePair> checkIfWhiteFirstMove(PieceTeamProvider pieceTeamProvider, CoordinatePair from) {
        if (getType().getTeam() == Team.WHITE &&
            from.matchY(CoordinateY.RANK_2)) {
            return move2Cells(pieceTeamProvider, from, Direction.UP);
        }
        return Optional.empty();
    }

    private Set<CoordinatePair> handleWhenBlack(PieceTeamProvider pieceTeamProvider, CoordinatePair from) {
        Set<CoordinatePair> movableCoordinates = new HashSet<>();
        from.move(Direction.DOWN)
            .filter(coord -> pieceTeamProvider.getTeamAt(coord).isEmpty())
            .ifPresent(movableCoordinates::add);
        from.move(Direction.LEFT_BOTTOM)
            .filter(coord -> pieceTeamProvider.getTeamAt(coord).isEnemy(getType().getTeam()))
            .ifPresent(movableCoordinates::add);
        from.move(Direction.RIGHT_BOTTOM)
            .filter(coord -> pieceTeamProvider.getTeamAt(coord).isEnemy(getType().getTeam()))
            .ifPresent(movableCoordinates::add);

        checkIfBlackFirstMove(pieceTeamProvider, from)
            .ifPresent(movableCoordinates::add);
        return movableCoordinates;
    }

    private Optional<CoordinatePair> checkIfBlackFirstMove(PieceTeamProvider pieceTeamProvider, CoordinatePair from) {
        if (getType().getTeam() == Team.BLACK &&
            from.matchY(CoordinateY.RANK_7)) {
            return move2Cells(pieceTeamProvider, from, Direction.DOWN);
        }
        return Optional.empty();
    }

    private Optional<CoordinatePair> move2Cells(PieceTeamProvider pieceTeamProvider, CoordinatePair from, Direction direction) {
        return from.move(direction)
            .map(coordMoved1 -> coordMoved1.move(direction)
                .filter(coordMoved2 -> pieceTeamProvider.getTeamAt(coordMoved2).isEmpty())
                .orElse(null));
    }
}
