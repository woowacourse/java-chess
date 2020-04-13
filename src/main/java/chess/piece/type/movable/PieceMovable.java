package chess.piece.type.movable;

import chess.board.Route;
import chess.location.Location;

public interface PieceMovable {
    boolean canMove(Route route);

    default boolean hasNotObstacle(Route route) {
        int weight = 1;
        Location now = route.getNow();
        Location destination = route.getDestination();

        Location nowLocation = now.calculateNextLocation(destination, weight);
        while (!nowLocation.equals(destination)) {
            if (route.isExistPieceIn(nowLocation)) {
                return false;
            }
            weight++;
            nowLocation = now.calculateNextLocation(destination, weight);
        }
        return true;
    }
}
