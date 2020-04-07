package chess.board;

import chess.location.Location;
import chess.piece.type.Piece;

import java.util.Map;
import java.util.Optional;

import static java.lang.Math.abs;

public class Route {
    private final Map<Location, Optional<Piece>> route;
    private final Location now;
    private final Location destination;

    public Route(Map<Location, Optional<Piece>> route, Location now, Location destination) {
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
        Optional<Piece> optionalDestinationPiece = route.get(destination);
        if(optionalDestinationPiece == null) {
            return true;
        }
        return false;
    }


    public boolean isEnemyNowAndDestination() {
        Optional<Piece> optionalNowPiece = route.get(now);
        Piece nowPiece = optionalNowPiece.get();

        Optional<Piece> optionalDestinationPiece = route.get(destination);

        if(optionalDestinationPiece.isPresent()) {
            Piece destinationPiece = optionalDestinationPiece.get();
            return nowPiece.isReverseTeam(destinationPiece);
        }

        return false;
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
        for (Map.Entry<Location, Optional<Piece>> entry : route.entrySet()) {
            if(entry.getValue() != null) {
                sb.append(entry.getKey() + " : " + entry.getValue());
            }
            sb.append(entry.getKey() + " : " + "null");
        }
        return sb.toString();
    }

    public boolean isNotEmpty(Location nowLocation) {
        return route.get(nowLocation) != null;
    }
}
