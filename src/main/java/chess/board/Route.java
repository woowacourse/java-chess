package chess.board;

import chess.location.Location;
import chess.piece.type.Piece;

import java.util.Map;
import java.util.Optional;

import static java.lang.Math.abs;
import static java.lang.Math.log;

public class Route {
    private final Map<Location, Piece> route;
    private final Location now;
    private final Location destination;

    public Route(Map<Location, Piece> route, Location now, Location destination) {
        this.route = route;
        this.now = now;
        this.destination = destination;
    }

    public boolean isDiagonal() {
        return abs(now.getRowValue() - destination.getRowValue())
                == abs(now.getColValue() - destination.getColValue());
    }

    public boolean isStraight() {
        return now.isSameRow(destination) || isVertical();
    }

    public boolean isForwardDiagonal(int value) {
        int colGap = Math.abs(destination.getColValue() - now.getColValue());
        Location forwardRowLocation = now.plusRowBy(value);
        return forwardRowLocation.isSameRow(destination) && colGap == 1;
    }

    private boolean isVertical() {
        return now.isSameCol(destination);
    }

    public boolean isEmptyDestinaion() {
        return route.containsKey(destination) == false;
    }

    public boolean isEnemyNowAndDestination() {
        Piece nowPiece = route.get(now);
        Piece destinationPiece = route.get(destination);

        return nowPiece.isReverseTeam(destinationPiece);
    }

    public boolean isEmpty(Location location) {
        return !route.containsKey(location);
    }

    public boolean isExistPieceIn(Location location) {
        return route.containsKey(location);
    }

    public Location getNow() {
        return now;
    }

    public Location getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Location, Piece> entry : route.entrySet()) {
            sb.append(entry.getKey())
                    .append(" : ")
                    .append(entry.getValue());
        }
        return sb.toString();
    }
}
