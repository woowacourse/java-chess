package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Rook extends ChessPiece {

    public Rook(Team team) {
        super(getPieceTypeByTeam(team));
    }

    private static PieceType getPieceTypeByTeam(Team team) {
        if (team == Team.BLACK) {
            return PieceType.ROOK_BLACK;
        }
        if (team == Team.WHITE) {
            return PieceType.ROOK_WHITE;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public List<ChessCoordinate> getMovableCoordinates(ChessBoard board, ChessCoordinate from) {
        List<ChessCoordinate> movableCoords = new ArrayList<>();

        ChessXCoordinate fromX = from.getX();
        ChessYCoordinate fromY = from.getY();

        for (ChessXCoordinate x : ChessXCoordinate.getAscendingCoordinates(fromX)) {
            getIfEmpty(board, x, fromY).ifPresent(movableCoords::add);
            Optional<ChessCoordinate> maybeEnemyLocation = getIfEnemy(board, x, fromY);
            if (maybeEnemyLocation.isPresent()) {
                movableCoords.add(maybeEnemyLocation.get());
                break;
            }
        }

        for (ChessXCoordinate x : ChessXCoordinate.getDescendingCoordinates(fromX)) {
            getIfEmpty(board, x, fromY).ifPresent(movableCoords::add);
            Optional<ChessCoordinate> maybeEnemyLocation = getIfEnemy(board, x, fromY);
            if (maybeEnemyLocation.isPresent()) {
                movableCoords.add(maybeEnemyLocation.get());
                break;
            }
        }

        for (ChessYCoordinate y : ChessYCoordinate.getAscendingCoordinates(fromY)) {
            getIfEmpty(board, fromX, y).ifPresent(movableCoords::add);
            Optional<ChessCoordinate> maybeEnemyLocation = getIfEnemy(board, fromX, y);
            if (maybeEnemyLocation.isPresent()) {
                movableCoords.add(maybeEnemyLocation.get());
                break;
            }
        }

        for (ChessYCoordinate y : ChessYCoordinate.getDescendingCoordinates(fromY)) {
            getIfEmpty(board, fromX, y).ifPresent(movableCoords::add);
            Optional<ChessCoordinate> maybeEnemyLocation = getIfEnemy(board, fromX, y);
            if (maybeEnemyLocation.isPresent()) {
                movableCoords.add(maybeEnemyLocation.get());
                break;
            }
        }

        return movableCoords;
    }

    private Optional<ChessCoordinate> getIfEmpty(ChessBoard board, ChessXCoordinate x, ChessYCoordinate y) {
        if (board.getTeamAt(x, y) == Team.NEUTRAL) {
            return Optional.of(ChessCoordinate.valueOf(x, y));
        }
        return Optional.empty();
    }

    private Optional<ChessCoordinate> getIfEnemy(ChessBoard board, ChessXCoordinate x, ChessYCoordinate y) {
        Team targetTeam = board.getTeamAt(x, y);

        if ((getType().getTeam() == Team.BLACK && targetTeam == Team.WHITE) ||
                (getType().getTeam() == Team.WHITE && targetTeam == Team.BLACK)) {
            return Optional.of(ChessCoordinate.valueOf(x, y));
        }
        return Optional.empty();
    }
}
