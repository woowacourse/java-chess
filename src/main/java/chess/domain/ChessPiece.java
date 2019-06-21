package chess.domain;

import java.util.*;

public abstract class ChessPiece {
    private PieceType type;

    protected ChessPiece(PieceType type) {
        this.type = type;
    }

    public PieceType getType() {
        return type;
    }

    abstract Set<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from);

    protected Optional<ChessCoordinate> getIfEmpty(PieceTeamProvider pieceTeamProvider, Optional<ChessCoordinate> maybeCoord) {
        if (!maybeCoord.isPresent()) {
            return Optional.empty();
        }
        ChessCoordinate coord = maybeCoord.get();
        if (pieceTeamProvider.getTeamAt(coord) == Team.NEUTRAL) {
            return maybeCoord;
        }
        return Optional.empty();
    }

    protected Optional<ChessCoordinate> getIfEnemy(PieceTeamProvider pieceTeamProvider, Optional<ChessCoordinate> maybeCoord) {
        if (!maybeCoord.isPresent()) {
            return Optional.empty();
        }
        ChessCoordinate coord = maybeCoord.get();
        Team targetTeam = pieceTeamProvider.getTeamAt(coord);

        if ((getType().getTeam() == Team.BLACK && targetTeam == Team.WHITE) ||
                (getType().getTeam() == Team.WHITE && targetTeam == Team.BLACK)) {
            return maybeCoord;
        }
        return Optional.empty();
    }

    protected Optional<ChessCoordinate> getIfAlly(PieceTeamProvider pieceTeamProvider, Optional<ChessCoordinate> maybeCoord) {
        if (!maybeCoord.isPresent()) {
            return Optional.empty();
        }
        ChessCoordinate coord = maybeCoord.get();
        if (getType().getTeam() == pieceTeamProvider.getTeamAt(coord)) {
            return Optional.of(coord);
        }
        return Optional.empty();
    }

    protected Set<ChessCoordinate> probeHorizon(PieceTeamProvider pieceTeamProvider, ChessXCoordinate fromX, List<ChessYCoordinate> ascendingCoordinates) {
        Set<ChessCoordinate> movableCoords = new HashSet<>();
        for (ChessYCoordinate y : ascendingCoordinates) {
            getIfEmpty(pieceTeamProvider, ChessCoordinate.valueOf(fromX, y)).ifPresent(movableCoords::add);
            Optional<ChessCoordinate> maybeEnemyLocation = getIfEnemy(pieceTeamProvider, ChessCoordinate.valueOf(fromX, y));
            if (maybeEnemyLocation.isPresent()) {
                movableCoords.add(maybeEnemyLocation.get());
                break;
            }
            Optional<ChessCoordinate> maybeAllyLocation = getIfAlly(pieceTeamProvider, ChessCoordinate.valueOf(fromX, y));
            if (maybeAllyLocation.isPresent()) {
                break;
            }
        }
        return movableCoords;
    }

    protected Set<ChessCoordinate> probeVertical(PieceTeamProvider pieceTeamProvider, List<ChessXCoordinate> ascendingCoordinates, ChessYCoordinate fromY) {
        Set<ChessCoordinate> movableCoords = new HashSet<>();
        for (ChessXCoordinate x : ascendingCoordinates) {
            getIfEmpty(pieceTeamProvider, ChessCoordinate.valueOf(x, fromY)).ifPresent(movableCoords::add);
            Optional<ChessCoordinate> maybeEnemyLocation = getIfEnemy(pieceTeamProvider, ChessCoordinate.valueOf(x, fromY));
            if (maybeEnemyLocation.isPresent()) {
                movableCoords.add(maybeEnemyLocation.get());
                break;
            }
            Optional<ChessCoordinate> maybeAllyLocation = getIfAlly(pieceTeamProvider, ChessCoordinate.valueOf(x, fromY));
            if (maybeAllyLocation.isPresent()) {
                break;
            }
        }
        return movableCoords;
    }

    protected Set<ChessCoordinate> probeAllDiagonal(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {
        Set<ChessCoordinate> movableCoords = new HashSet<>();

        List<ChessXCoordinate> xDescendCoords = ChessXCoordinate.getDescendingCoordinates(from.getX());
        List<ChessYCoordinate> yDescendCoords = ChessYCoordinate.getDescendingCoordinates(from.getY());
        List<ChessXCoordinate> xAscendCoords = ChessXCoordinate.getAscendingCoordinates(from.getX());
        List<ChessYCoordinate> yAscendCoords = ChessYCoordinate.getAscendingCoordinates(from.getY());

        for (List<ChessXCoordinate> xCoord : Arrays.asList(xDescendCoords, xAscendCoords)) {
            for (List<ChessYCoordinate> yCoord : Arrays.asList(yDescendCoords, yAscendCoords)) {
                movableCoords.addAll(probeDiagonal(pieceTeamProvider, xCoord, yCoord));
            }
        }
        return movableCoords;
    }

    private Set<ChessCoordinate> probeDiagonal(PieceTeamProvider pieceTeamProvider, List<ChessXCoordinate> xCoords, List<ChessYCoordinate> yCoords) {
        Set<ChessCoordinate> movableCoords = new HashSet<>();

        for (int i = 0; i < Math.min(xCoords.size(), yCoords.size()); i++) {
            getIfEmpty(pieceTeamProvider, ChessCoordinate.valueOf(xCoords.get(i), yCoords.get(i))).ifPresent(movableCoords::add);
            Optional<ChessCoordinate> maybeEnemyLocation = getIfEnemy(pieceTeamProvider, ChessCoordinate.valueOf(xCoords.get(i), yCoords.get(i)));
            if (maybeEnemyLocation.isPresent()) {
                movableCoords.add(maybeEnemyLocation.get());
                break;
            }
            Optional<ChessCoordinate> maybeAllyLocation = getIfAlly(pieceTeamProvider, ChessCoordinate.valueOf(xCoords.get(i), yCoords.get(i)));
            if (maybeAllyLocation.isPresent()) {
                break;
            }
        }
        return movableCoords;
    }
}
