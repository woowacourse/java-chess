package chess.domain.piece;

import chess.domain.*;
import chess.domain.coordinate.ChessCoordinate;
import chess.domain.coordinate.ChessXCoordinate;
import chess.domain.coordinate.ChessYCoordinate;

import java.util.*;
import java.util.function.Consumer;

public abstract class ChessPiece {
    protected final static int INCREASE_ONE = 1;
    protected final static int DECREASE_ONE = -1;
    protected final static int INCREASE_TWO = 2;
    protected final static int DECREASE_TWO = -2;

    private PieceType type;

    protected ChessPiece(PieceType type) {
        this.type = type;
    }

    public PieceType getType() {
        return type;
    }

    abstract Set<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from);

    protected Set<ChessCoordinate> probCross(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {
        Set<ChessCoordinate> movableCoords = new HashSet<>();

        ChessXCoordinate fromX = from.getX();
        ChessYCoordinate fromY = from.getY();

        movableCoords.addAll(probeVertical(pieceTeamProvider, ChessXCoordinate.getAscendingCoordinates(fromX), fromY));
        movableCoords.addAll(probeVertical(pieceTeamProvider, ChessXCoordinate.getDescendingCoordinates(fromX), fromY));

        movableCoords.addAll(probeHorizon(pieceTeamProvider, fromX, ChessYCoordinate.getAscendingCoordinates(fromY)));
        movableCoords.addAll(probeHorizon(pieceTeamProvider, fromX, ChessYCoordinate.getDescendingCoordinates(fromY)));
        return movableCoords;
    }

    private Set<ChessCoordinate> probeHorizon(PieceTeamProvider pieceTeamProvider, ChessXCoordinate fromX, List<ChessYCoordinate> ascendingCoordinates) {
        Set<ChessCoordinate> movableCoords = new HashSet<>();
        Optional<ChessCoordinate> maybeCoord = Optional.empty();

        for (ChessYCoordinate y : ascendingCoordinates) {
            ChessCoordinate coord = ChessCoordinate.valueOf(fromX, y);
            getIfEmpty(pieceTeamProvider, coord).ifPresent(movableCoords::add);

            if (isPiece(pieceTeamProvider, coord)) {
                maybeCoord = Optional.of(coord);
                break;
            }
        }
        maybeCoord.ifPresent(coord -> getIfEnemy(pieceTeamProvider, coord).ifPresent(movableCoords::add));

        return movableCoords;
    }

    private Set<ChessCoordinate> probeVertical(PieceTeamProvider pieceTeamProvider, List<ChessXCoordinate> ascendingCoordinates, ChessYCoordinate fromY) {
        Set<ChessCoordinate> movableCoords = new HashSet<>();
        Optional<ChessCoordinate> maybeCoord = Optional.empty();

        for (ChessXCoordinate x : ascendingCoordinates) {
            ChessCoordinate coord = ChessCoordinate.valueOf(x, fromY);
            getIfEmpty(pieceTeamProvider, coord).ifPresent(movableCoords::add);

            if (isPiece(pieceTeamProvider, coord)) {
                maybeCoord = Optional.of(coord);
                break;
            }
        }
        maybeCoord.ifPresent(coord -> getIfEnemy(pieceTeamProvider, coord).ifPresent(movableCoords::add));

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
        Optional<ChessCoordinate> maybeCoord = Optional.empty();

        for (int i = 0; i < Math.min(xCoords.size(), yCoords.size()); i++) {
            ChessCoordinate coord = ChessCoordinate.valueOf(xCoords.get(i), yCoords.get(i));
            getIfEmpty(pieceTeamProvider, coord).ifPresent(movableCoords::add);

            if (isPiece(pieceTeamProvider, coord)) {
                maybeCoord = Optional.of(coord);
                break;
            }
        }
        maybeCoord.ifPresent(coord -> getIfEnemy(pieceTeamProvider, coord).ifPresent(movableCoords::add));

        return movableCoords;
    }

    protected Consumer<ChessXCoordinate> proveYSide(ChessCoordinate from, List<ChessCoordinate> candidates) {
        return x -> {
            from.getY().move(DECREASE_ONE).ifPresent(y -> candidates.add(ChessCoordinate.valueOf(x, y)));
            from.getY().move(INCREASE_ONE).ifPresent(y -> candidates.add(ChessCoordinate.valueOf(x, y)));
        };
    }

    protected Consumer<ChessYCoordinate> proveXSide(ChessCoordinate from, List<ChessCoordinate> candidates) {
        return (y) -> {
            from.getX().move(DECREASE_ONE).ifPresent(x -> candidates.add(ChessCoordinate.valueOf(x, y)));
            from.getX().move(INCREASE_ONE).ifPresent(x -> candidates.add(ChessCoordinate.valueOf(x, y)));
        };
    }

    protected Optional<ChessCoordinate> getIfEmpty(PieceTeamProvider pieceTeamProvider, ChessCoordinate coord) {
        if (pieceTeamProvider.getTeamAt(coord) == Team.NEUTRAL) {
            return Optional.of(coord);
        }
        return Optional.empty();
    }

    protected Optional<ChessCoordinate> getIfEnemy(PieceTeamProvider pieceTeamProvider, ChessCoordinate coord) {
        Team targetTeam = pieceTeamProvider.getTeamAt(coord);

        if (targetTeam != Team.NEUTRAL && getType().getTeam() != targetTeam) {
            return Optional.of(coord);
        }
        return Optional.empty();
    }

    protected boolean isPiece(PieceTeamProvider pieceTeamProvider, ChessCoordinate coord) {
        if (pieceTeamProvider.getTeamAt(coord) != Team.NEUTRAL) {
            return true;
        }
        return false;
    }
}
