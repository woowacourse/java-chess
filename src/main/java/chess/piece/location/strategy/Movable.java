package chess.piece.location.strategy;

import chess.board.Location;

public interface Movable {
	boolean isMovable(Location location, boolean isBlack);
}
