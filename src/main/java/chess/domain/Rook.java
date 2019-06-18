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
    public List<ChessCoordinate> getMovableCoordinates(PieceTeamProvider pieceTeamProvider, ChessCoordinate from) {
        List<ChessCoordinate> movableCoords = new ArrayList<>();

        ChessXCoordinate fromX = from.getX();
        ChessYCoordinate fromY = from.getY();

        for (ChessXCoordinate x : ChessXCoordinate.getAscendingCoordinates(fromX)) {
            getIfEmpty(pieceTeamProvider, x, fromY).ifPresent(movableCoords::add);
            Optional<ChessCoordinate> maybeEnemyLocation = getIfEnemy(pieceTeamProvider, x, fromY);
            if (maybeEnemyLocation.isPresent()) {
                movableCoords.add(maybeEnemyLocation.get());
                break;
            }
        }

        for (ChessXCoordinate x : ChessXCoordinate.getDescendingCoordinates(fromX)) {
            getIfEmpty(pieceTeamProvider, x, fromY).ifPresent(movableCoords::add);
            Optional<ChessCoordinate> maybeEnemyLocation = getIfEnemy(pieceTeamProvider, x, fromY);
            if (maybeEnemyLocation.isPresent()) {
                movableCoords.add(maybeEnemyLocation.get());
                break;
            }
        }

        for (ChessYCoordinate y : ChessYCoordinate.getAscendingCoordinates(fromY)) {
            getIfEmpty(pieceTeamProvider, fromX, y).ifPresent(movableCoords::add);
            Optional<ChessCoordinate> maybeEnemyLocation = getIfEnemy(pieceTeamProvider, fromX, y);
            if (maybeEnemyLocation.isPresent()) {
                movableCoords.add(maybeEnemyLocation.get());
                break;
            }
        }

        for (ChessYCoordinate y : ChessYCoordinate.getDescendingCoordinates(fromY)) {
            getIfEmpty(pieceTeamProvider, fromX, y).ifPresent(movableCoords::add);
            Optional<ChessCoordinate> maybeEnemyLocation = getIfEnemy(pieceTeamProvider, fromX, y);
            if (maybeEnemyLocation.isPresent()) {
                movableCoords.add(maybeEnemyLocation.get());
                break;
            }
        }

        return movableCoords;
    }
}
