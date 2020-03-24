package chess.piece.location.strategy;

import java.util.List;

import chess.board.Location;

public interface LocationStrategy {
	List<Location> getInitialLocation();

	List<Location> reverseInitialLocation();
}
