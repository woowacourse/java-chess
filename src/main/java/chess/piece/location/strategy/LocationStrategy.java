package chess.piece.location.strategy;

import chess.board.Location;

import java.util.List;

public interface LocationStrategy {
    List<Location> getInitialLocation();

    List<Location> reverseIntialLocation();
}
