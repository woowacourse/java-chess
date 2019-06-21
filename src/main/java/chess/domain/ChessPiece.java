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

    protected Set<ChessCoordinate> probeVerticalAndHorizaon(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {
        Set<ChessCoordinate> movableCoords = new HashSet<>();
        ChessXCoordinate fromX = from.getX();
        ChessYCoordinate fromY = from.getY();

        movableCoords.addAll(probeVertical(pieceTeamProvider, ChessXCoordinate.getAscendingCoordinates(fromX), fromY));
        movableCoords.addAll(probeVertical(pieceTeamProvider, ChessXCoordinate.getDescendingCoordinates(fromX), fromY));

        movableCoords.addAll(probeHorizon(pieceTeamProvider, fromX, ChessYCoordinate.getAscendingCoordinates(fromY)));
        movableCoords.addAll(probeHorizon(pieceTeamProvider, fromX, ChessYCoordinate.getDescendingCoordinates(fromY)));
        return movableCoords;
    }

    private Optional<ChessCoordinate> getIfEmpty(PieceTeamProvider pieceTeamProvider, Optional<ChessCoordinate> maybeCoord) {
        if (!maybeCoord.isPresent()) {
            return Optional.empty();
        }
        ChessCoordinate coord = maybeCoord.get();
        if (pieceTeamProvider.getTeamAt(coord) == Team.NEUTRAL) {
            return maybeCoord;
        }
        return Optional.empty();
    }

    private Optional<ChessCoordinate> getIfEnemy(PieceTeamProvider pieceTeamProvider, Optional<ChessCoordinate> maybeCoord) {
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

    private Optional<ChessCoordinate> getIfAlly(PieceTeamProvider pieceTeamProvider, Optional<ChessCoordinate> maybeCoord) {
        if (!maybeCoord.isPresent()) {
            return Optional.empty();
        }
        ChessCoordinate coord = maybeCoord.get();
        if (getType().getTeam() == pieceTeamProvider.getTeamAt(coord)) {
            return Optional.of(coord);
        }
        return Optional.empty();
    }

    private Set<ChessCoordinate> probeHorizon(PieceTeamProvider pieceTeamProvider, ChessXCoordinate fromX, List<ChessYCoordinate> ascendingCoordinates) {
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

    private Set<ChessCoordinate> probeVertical(PieceTeamProvider pieceTeamProvider, List<ChessXCoordinate> ascendingCoordinates, ChessYCoordinate fromY) {
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

    protected Set<ChessCoordinate> probeDiagonal(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {
        Set<ChessCoordinate> movableCoords = new HashSet<>();
        List<ChessXCoordinate> xCoords = ChessXCoordinate.getDescendingCoordinates(from.getX());
        List<ChessYCoordinate> yCoords = ChessYCoordinate.getDescendingCoordinates(from.getY());

        movableCoords.addAll(probeDiagonal(pieceTeamProvider, xCoords, yCoords));

        yCoords = ChessYCoordinate.getAscendingCoordinates(from.getY());
        movableCoords.addAll(probeDiagonal(pieceTeamProvider, xCoords, yCoords));

        xCoords = ChessXCoordinate.getAscendingCoordinates(from.getX());
        movableCoords.addAll(probeDiagonal(pieceTeamProvider, xCoords, yCoords));

        yCoords = ChessYCoordinate.getDescendingCoordinates(from.getY());
        movableCoords.addAll(probeDiagonal(pieceTeamProvider, xCoords, yCoords));
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
