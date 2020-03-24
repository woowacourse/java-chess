package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class KingMoveStrategy implements MoveStrategy {
	@Override
	public boolean isNotMovableTo(Position start, Position destination) {
		List<Position> movableArea = new ArrayList<>();
		movableArea.add(start.up());
		movableArea.add(start.down());
		movableArea.add(start.right());
		movableArea.add(start.left());
		movableArea.add(start.up().right());
		movableArea.add(start.up().left());
		movableArea.add(start.down().right());
		movableArea.add(start.down().left());
		return !movableArea.contains(destination);
	}

	@Override
	public boolean isSameColorIn(Position position) {
		return false;
	}
}
