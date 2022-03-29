package chess.direction;

import chess.direction.route.Route;

public interface Direction {

    boolean findRouteFrom(int rankDifference, int fileDifference);

    Route getRoute();
}
