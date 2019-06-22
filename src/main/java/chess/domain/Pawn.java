package chess.domain;

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
        super(getPieceTypeByTeam(team));
    }

    private static PieceType getPieceTypeByTeam(Team team) {
        if (team == Team.BLACK) {
            return PieceType.PAWN_BLACK;
        }
        if (team == Team.WHITE) {
            return PieceType.PAWN_WHITE;
        }
        throw new IllegalArgumentException();
    }

    @Override
    Set<CoordinatePair> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, CoordinatePair from) {
        if (from.getY() == CoordinateY.RANK_1 || from.getY() == CoordinateY.RANK_8) {
            return Collections.emptySet();
        }
        if (getType().getTeam() == Team.WHITE) {
            return handleWhenWhite(pieceTeamProvider, from);
        }

        return handleWhenBlack(pieceTeamProvider, from);
    }

    private Set<CoordinatePair> handleWhenWhite(PieceTeamProvider pieceTeamProvider, CoordinatePair from) {
        Set<CoordinatePair> movableCoordinates = new HashSet<>();
        Direction.UP.move(from)
            .filter(coord -> pieceTeamProvider.getTeamAt(coord).isEmpty())
            .ifPresent(movableCoordinates::add);
        Direction.LEFT_TOP.move(from)
            .filter(coord -> pieceTeamProvider.getTeamAt(coord).isEnemy(getType().getTeam()))
            .ifPresent(movableCoordinates::add);
        Direction.RIGHT_TOP.move(from)
            .filter(coord -> pieceTeamProvider.getTeamAt(coord).isEnemy(getType().getTeam()))
            .ifPresent(movableCoordinates::add);

        checkIfWhiteFirstMove(pieceTeamProvider, from)
            .ifPresent(movableCoordinates::add);

        return movableCoordinates;
    }

    private Optional<CoordinatePair> checkIfWhiteFirstMove(PieceTeamProvider pieceTeamProvider, CoordinatePair from) {
        if (getType().getTeam() == Team.WHITE &&
            from.getY() == CoordinateY.RANK_2) {
            return move2Cells(pieceTeamProvider, from, Direction.UP);
        }
        return Optional.empty();
    }

    private Set<CoordinatePair> handleWhenBlack(PieceTeamProvider pieceTeamProvider, CoordinatePair from) {
        Set<CoordinatePair> movableCoordinates = new HashSet<>();
        Direction.DOWN.move(from)
            .filter(coord -> pieceTeamProvider.getTeamAt(coord).isEmpty())
            .ifPresent(movableCoordinates::add);
        Direction.LEFT_BOTTOM.move(from)
            .filter(coord -> pieceTeamProvider.getTeamAt(coord).isEnemy(getType().getTeam()))
            .ifPresent(movableCoordinates::add);
        Direction.RIGHT_BOTTOM.move(from)
            .filter(coord -> pieceTeamProvider.getTeamAt(coord).isEnemy(getType().getTeam()))
            .ifPresent(movableCoordinates::add);

        checkIfBlackFirstMove(pieceTeamProvider, from)
            .ifPresent(movableCoordinates::add);
        return movableCoordinates;
    }

    private Optional<CoordinatePair> checkIfBlackFirstMove(PieceTeamProvider pieceTeamProvider, CoordinatePair from) {
        if (getType().getTeam() == Team.BLACK &&
            from.getY() == CoordinateY.RANK_8) {
            return move2Cells(pieceTeamProvider, from, Direction.DOWN);
        }
        return Optional.empty();
    }

    private Optional<CoordinatePair> move2Cells(PieceTeamProvider pieceTeamProvider, CoordinatePair from, Direction direction) {
        return direction.move(from)
            .map(coordMoved1 -> direction.move(coordMoved1)
                .filter(coordMoved2 -> pieceTeamProvider.getTeamAt(coordMoved2).isEmpty())
                .orElse(null));
    }
}
