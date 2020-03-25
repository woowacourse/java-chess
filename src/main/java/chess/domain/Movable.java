package chess.domain;

import java.util.Set;

public interface Movable {
	Set<Position> move(Position position);
}
