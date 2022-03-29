package chess.model.direction;

import chess.model.direction.route.Route;

public interface Direction {

    boolean findRouteFrom(int rankDifference, int fileDifference);

    Route getRoute();
}
